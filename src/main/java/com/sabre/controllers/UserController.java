package com.sabre.controllers;

import com.sabre.domain.User;
import com.sabre.persistance.UserDatabaseRepository;
import com.sabre.services.GeoCodeService;
import com.sabre.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @RequestMapping( path = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( userService.getAllUsers(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping( path = "/testLogin/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<User> login(@PathVariable("email") final String email,
                                      @RequestParam("name") final String name){
        logger.info("Attempt to log as " + email);
        HttpHeaders responseHeaders = new HttpHeaders();
        if ( userService.validateUser(email, name) ){
            logger.info("Successful login " + email);
            return new ResponseEntity<>( userService.getUserByEmail(email).get(), responseHeaders, HttpStatus.OK);
        } else{
            logger.info("Unsuccessful login " + email);
            return new ResponseEntity<>( null, responseHeaders, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping( path = "/getUser/{email:.+}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") final String email){
        final HttpHeaders responseHeaders = new HttpHeaders();
        return userService.getUserByEmail(email).map( x ->
             new ResponseEntity<>( x, responseHeaders, HttpStatus.OK)
        ).orElse(
             new ResponseEntity<>( null, responseHeaders, HttpStatus.CONFLICT)
        );

    }

    @RequestMapping( path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody() final User user){
        userService.addUser(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( user, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping( path = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody() final long userId){
        userService.deleteUser(userId);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>( "User deleted", responseHeaders, HttpStatus.ACCEPTED);
    }




}
