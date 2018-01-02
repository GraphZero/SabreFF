'use strict'
/**
 * Created by Andrzej on 2017-11-30.
 */

angular.module('shared').service( 'FlightsService', function() {

    this.getFlightsByUserEmail = function( userEmail, $http ) {
        var absUrl = "/getFlightsByUserEmail/" + userEmail;
        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            console.log("Fail!");
        });
    };


});