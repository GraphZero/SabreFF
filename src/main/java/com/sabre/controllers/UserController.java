package com.sabre.controllers;

import com.sabre.domain.UserEntity;
import com.sabre.services.ParseDataFromCsvFileAndInsertToDatabaseService;
import com.sabre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Andrzej on 2017-11-09.
 */

@RestController
public class UserController {
    UserService userService;
    ParseDataFromCsvFileAndInsertToDatabaseService parseDataFromCsvFileAndInsertToDatabaseService;

    @Autowired
    public UserController(UserService userService, ParseDataFromCsvFileAndInsertToDatabaseService parseDataFromCsvFileAndInsertToDatabaseService) {
        this.userService = userService;
        this.parseDataFromCsvFileAndInsertToDatabaseService = parseDataFromCsvFileAndInsertToDatabaseService;
    }

    @RequestMapping( path = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.getAllUsers(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/parseUsers", method = RequestMethod.GET)
    public ResponseEntity<String> parseUsers(){
        try {
            parseDataFromCsvFileAndInsertToDatabaseService.readDataFromCsvFile();
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>( "Parsed", responseHeaders, HttpStatus.ACCEPTED);
        } catch (IOException e) {
            e.printStackTrace();
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>( "Couldnt Parse", responseHeaders, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping( path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody() final UserEntity userEntity){
        userService.addUser(userEntity);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( "OK!", responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping( path = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody() final long userId){
        userService.deleteUser(userId);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( "User deleted", responseHeaders, HttpStatus.ACCEPTED);
    }


}
