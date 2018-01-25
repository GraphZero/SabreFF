package com.sabre.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersistDataFromCsvFileServiceTest {

    @Autowired
    PersistDataFromCsvFileService persistDataFromCsvFileService;


    @Test
    public void readDataFromCsvFile() throws IOException{
        persistDataFromCsvFileService.cacheDataFromCsvFile();
    }


}