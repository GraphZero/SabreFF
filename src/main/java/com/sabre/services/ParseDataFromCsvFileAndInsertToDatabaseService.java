package com.sabre.services;

import com.sabre.domain.UserEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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
    private UserService userService;
    private ResourceLoader resourceLoader;
    private final String[] HEADERS = {"firstName", "secondName", "email", "airportDepartureCode", "airportArrivalCode",
            "airLineCode", "miles", "class", "returnTicketOrOneWay", "departureDate", "arrivalDate"};

    @Autowired
    public ParseDataFromCsvFileAndInsertToDatabaseService(UserService userService, ResourceLoader resourceLoader) {
        this.userService = userService;
        this.resourceLoader = resourceLoader;
    }

    public void readDataFromCsvFile() throws IOException {
        Resource csvResource = resourceLoader.getResource("classpath:static/csv/usersWithFlights.csv");
        Reader in = new FileReader(csvResource.getFile());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withDelimiter(';')
                .withHeader(HEADERS)
                .parse(in);

        insertUsersToDatabase(records);
    }

    private void insertUsersToDatabase(Iterable<CSVRecord> records) {
        int userId = 0;
        for (CSVRecord record : records) {
            if ( !userService.isUserInDatabase(record.get("email")) ) {
                userService.addUser(new UserEntity(record.get("firstName"), record.get("secondName"),
                        record.get("email"), Double.parseDouble(record.get("miles")),
                        userId ));
                userId++;
            } else{
                UserEntity user = userService.getUserByEmail(record.get("email"));
                user.setMiles( user.getMiles() + Double.parseDouble(record.get("miles") ));
            }
        }
    }

}
