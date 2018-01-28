'use strict'

angular.module('home').controller('AddFlightCtrl', function($scope, $http, $location, DataService){

    $scope.addFlight = function(){
        var absUrl = "/postFlight/";
        var flight = {
            userEmail: "noah.williams@travel-sabre.com",
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
    }


});
