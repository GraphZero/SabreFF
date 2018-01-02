package com.sabre.services;

import com.sabre.domain.FlightEntity;
import com.sabre.domain.UserEntity;
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
 * Created by Andrzej on 2017-11-15.
 */

@Service
public class ParseDataFromCsvFileAndInsertToDatabaseService {
    private static Logger logger = LogManager.getLogger(ParseDataFromCsvFileAndInsertToDatabaseService.class);
    private UserService userService;
    private FlightsService flightsService;
    private ResourceLoader resourceLoader;
    private final String[] HEADERS = {"firstName", "secondName", "email", "airportDepartureCode", "airportArrivalCode",
            "airLineCode", "miles", "class", "returnTicketOrOneWay", "departureDate", "arrivalDate"};

    @Autowired
    public ParseDataFromCsvFileAndInsertToDatabaseService(UserService userService,  FlightsService flightsService,
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
        int userId = 0;
        int flightId = 0;
        for (CSVRecord record : records) {
            if ( !userService.isUserInDatabase(record.get("email")) ) {
                userService.addUser(new UserEntity(record.get("firstName"), record.get("secondName"),
                        record.get("email"), Double.parseDouble(record.get("miles")),
                        userId ));
                logger.info("Persisting user number " + userId);
                userId++;
            } else{
                UserEntity user = userService.getUserByEmail(record.get("email"));
                user.setMiles( user.getMiles() + Double.parseDouble(record.get("miles") ));
            }
            persistFlightToDatabase(flightId, record);
            logger.info("Persisting flight number " + flightId);
            flightId++;
        }
    }

    private void persistFlightToDatabase(long id, CSVRecord record) {
        flightsService.persistFlight( new FlightEntity( id,
                record.get("email"),
                Long.parseLong(record.get("miles").trim())
                , record.get("airportDepartureCode")
                , record.get("airportArrivalCode") ));
    }

}
