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
 * Used to parse data from provided CSV data file.
 */

@Service
public class PersistDataFromCsvFileService {
    private static Logger logger = LogManager.getLogger(PersistDataFromCsvFileService.class);
    private UserService userService;
    private FlightsService flightsService;
    private ResourceLoader resourceLoader;
    private final String[] HEADERS = {"firstName", "secondName", "email", "airportDepartureCode", "airportArrivalCode",
            "airLineCode", "miles", "class", "returnTicketOrOneWay", "departureDate", "arrivalDate"};

    @Autowired
    public PersistDataFromCsvFileService(UserService userService, FlightsService flightsService,
                                         ResourceLoader resourceLoader) {
        this.userService = userService;
        this.resourceLoader = resourceLoader;
        this.flightsService = flightsService;
    }

    public void readDataFromCsvFile() throws IOException {
        logger.info("Parsing data from file.");
        Resource csvResource = resourceLoader.getResource("classpath:static/csv/usersWithFlights.csv");
        Reader in = new FileReader(csvResource.getFile());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withHeader(HEADERS)
                .parse(in);

        persistUsersToDatabase(records);
    }

    private void persistUsersToDatabase(Iterable<CSVRecord> records) {
        int flightId = 0;
        for (CSVRecord record : records) {
            if ( !userService.isUserInDatabase(record.get("email")) ) {
                userService.addUser(new User(record.get("firstName"), record.get("secondName"),
                        record.get("email"), Double.parseDouble(record.get("miles"))));
                logger.info("Persisting user " );
            } else{
                User user = userService.getUserByEmail(record.get("email"));
                user.setMiles( user.getMiles() + Double.parseDouble(record.get("miles") ));
            }
            persistFlightToDatabase(flightId, record);
            logger.info("Persisting flight number " + flightId);
            flightId++;
        }
    }

    private void persistFlightToDatabase(long id, CSVRecord record) {
        flightsService.persistFlight( new Flight( id,
                record.get("email"),
                Long.parseLong(record.get("miles").trim())
                , record.get("airportDepartureCode")
                , record.get("airportArrivalCode") ));
    }

}
