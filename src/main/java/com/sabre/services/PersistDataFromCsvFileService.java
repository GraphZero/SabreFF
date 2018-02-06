package com.sabre.services;

import com.sabre.domain.Flight;
import com.sabre.domain.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Used to parse data from provided CSV data file. Sabre /geocode service works REALLY slow, so does this service.
 * Sadly I can't do anything to meaningfully decrease computing time.
 */

@Service
public class PersistDataFromCsvFileService {
    private static Logger logger = LogManager.getLogger(PersistDataFromCsvFileService.class);
    private UserService userService;
    private FlightsService flightsService;
    private ResourceLoader resourceLoader;
    private CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService;

    private final String[] HEADERS = {"firstName", "secondName", "email", "airportDepartureCode", "airportArrivalCode",
            "airLineCode", "id", "class", "returnTicketOrOneWay", "departureDate", "arrivalDate"};

    @Autowired
    public PersistDataFromCsvFileService(UserService userService,
                                         FlightsService flightsService,
                                         ResourceLoader resourceLoader,
                                         CalculateDistancesBetweenAirportsService calculateDistancesBetweenAirportsService) {
        this.userService = userService;
        this.resourceLoader = resourceLoader;
        this.flightsService = flightsService;
        this.calculateDistancesBetweenAirportsService = calculateDistancesBetweenAirportsService;
    }

    /**
     * Saves data from csv file to local db. Uses {@link com.sabre.services.CalculateDistancesBetweenAirportsService}
     * to get distance between two airports.
     * @throws IOException if there is no csv file
     */
    public void cacheDataFromCsvFile() throws IOException {
        logger.info("Parsing data from file.");
        Resource csvResource = resourceLoader.getResource("classpath:static/csv/usersWithFlights.csv");
        Reader in = new FileReader(csvResource.getFile());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withHeader(HEADERS)
                .parse(in);

        saveUsersToDatabase(records);
    }

    protected void saveUsersToDatabase(Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            if ( !userService.isUserInDatabase(record.get("email")) ) {
                userService.addUser(
                        new User(record.get("firstName"),
                        record.get("secondName"),
                        record.get("email"),
                        Double.parseDouble(record.get("id"))));
                logger.info("Persisting user " +  record.get("firstName"));
            } else{
                User user = userService.getUserByEmail(record.get("email")).get();
                user.setInitialMiles( user.getInitialMiles() + calculateDistancesBetweenAirportsService
                        .calculateDistance(record.get("airportDepartureCode"), record.get("airportArrivalCode")) );
            }
            saveFlightToDatabase( Long.parseLong(record.get("id").trim()) , record);
            logger.info("Persisting flight with id: " + Long.parseLong(record.get("id").trim()));
        }
    }

    protected void saveFlightToDatabase(long id, CSVRecord record) {
        double distance = calculateDistancesBetweenAirportsService
                .calculateDistance(record.get("airportDepartureCode"), record.get("airportArrivalCode"));
        if ( distance == -1 ){
            logger.warn("Couldn't calculate and save flight distance. ");
        } else{
            flightsService.persistFlight( new Flight( id,
                    record.get("email")
                    , (long) distance
                    , record.get("airportDepartureCode")
                    , record.get("airportArrivalCode") ));
        }

    }

}
