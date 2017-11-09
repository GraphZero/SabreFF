package com.sabre.controllers;

import com.sabre.persistance.UserDatabaseRepository;
import com.sabre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrzej on 2017-10-31.
 */

@RestController
public class MilesController {
    UserService userService;

    @Autowired
    public MilesController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping( path = "/getMiles/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Long> returnCurrentMiles(@PathVariable("userId") final long userID ){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.getMilesByUserId(userID), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/addMiles", method = RequestMethod.POST)
    public ResponseEntity<String> addMiles(@RequestParam("userId") final long userID, @RequestParam("miles") long miles ){
        userService.addMiles(miles, userID);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( "Done!", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/getMissingMiles/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Long> getMissingMiles(@PathVariable("userId") final long userId){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.getMissingMiles(userId), responseHeaders, HttpStatus.OK);
    }

}
