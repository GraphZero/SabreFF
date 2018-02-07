'use strict'

angular.module('home').controller('AddFlightCtrl', function($scope, $http, $location, DataService){

    function  postCompleteFlight(){
        var absUrl = "/postFlight/";
        var flight = {
            userEmail: DataService.getUser().email,
            airportDepartureCode: $scope.airportDepartureCode,
            airportArrivalCode: $scope.airportArrivalCode,
            airlineCode: $scope.airlineCode,
            miles: $scope.miles,
            flightClass: $scope.flightClass,
            returnTicket: $scope.returnTicket,
            departureFlightDate: $scope.departureFlightDate.getTime(),
            returnFlightDate: $scope.departureFlightDate.getTime()
        };
        var config = {
            method: 'POST',
            url: absUrl,
            accept: "application/json"
        };
        return $http.post( absUrl, flight, config)
            .then(
                function(){
                    DataService
                        .getUserData( DataService.getUser().email , $http)
                        .then(function successCallback() {
                            console.log("Successfully posted flight.");
                            $location.path( '/profilePage' );
                        }, function errorCallback() {
                        });
                },
                function(response){
                    console.log( response);
                    console.log("Couldnt posted flight.");
                });
    };

    function postInCompleteFlight(){
        var absUrl = "/postIncompleteFlight/" + DataService.getUser().email
            + "?airportDepartureCode=" + $scope.airportDepartureCode
            + "&airportArrivalCode=" + $scope.airportArrivalCode
            + "&airlineCode=" + $scope.airlineCode
            + "&flightClass=" + $scope.flightClass
            + "&returnTicket=" + $scope.returnTicket
            + "&departureFlightDate=" + $scope.departureFlightDate.getTime()
            + "&returnFlightDate=" + $scope.returnFlightDate.getTime();
        return $http.post( absUrl)
            .then(
                function(){
                    DataService
                        .getUserData( DataService.getUser().email , $http)
                        .then(function successCallback() {
                            console.log("Successfully posted incomplete flight.");
                            $location.path( '/profilePage' );
                        }, function errorCallback() {
                        });
                },
                function(response){
                    console.log( response);
                    console.log("Couldnt posted flight.");
                });
    };

    $scope.addFlight = function(){
        if ( $scope.miles ){
            postCompleteFlight();
        } else{
            postInCompleteFlight();
        }

    };
});
