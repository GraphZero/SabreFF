package com.sabre.controllers;

import com.sabre.persistance.UserDatabaseRepository;
import com.sabre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MilesController {
    private final UserService userService;

    @Autowired
    public MilesController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping( path = "/getMiles/{userEmail:.+}", method = RequestMethod.GET)
    public ResponseEntity<Double> returnCurrentMiles(@PathVariable("userId") final String userEmail ){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.getMilesByUserId(userEmail), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/addMiles", method = RequestMethod.POST)
    public ResponseEntity<String> addMilesForUser(@RequestParam("userId") final String userEmail, @RequestParam("miles") long miles ){
        userService.addMiles(miles, userEmail);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( "Done!", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/postMilesBetweenCities", method = RequestMethod.POST)
    public ResponseEntity<Double> getDistanceFromTwoCities(@RequestParam("userId") final String userEmail,
                                                           @RequestParam("codeOne") final String codeOne,
                                                           @RequestParam("codeTwo") final String codeTwo){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.addMilesByCities(userEmail, codeOne, codeTwo), responseHeaders, HttpStatus.OK);
    }

}
