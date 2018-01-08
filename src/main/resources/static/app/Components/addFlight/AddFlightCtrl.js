'use strict'

angular.module('home').controller('AddFlightCtrl', function($scope, $http){

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
                function(response){
                    console.log("Successfully posted flight.");
                    console.log(response.data);
                },
                function(response){
                    console.log( "|" + response.data);
                    console.log("Couldnt posted flight.");
                });
    }


});
