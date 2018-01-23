package com.sabre.services;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersistDataFromCsvFileServiceTest {

    @Autowired
    PersistDataFromCsvFileService persistDataFromCsvFileService;


    @Test
    public void readDataFromCsvFile() throws IOException{
        persistDataFromCsvFileService.readDataFromCsvFile();
    }


}