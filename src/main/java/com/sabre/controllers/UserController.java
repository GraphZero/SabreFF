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

@RestController
public class UserController {
    UserService userService;


    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @RequestMapping( path = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.getAllUsers(), responseHeaders, HttpStatus.OK);
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
