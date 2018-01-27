'use strict'

angular.module('home').controller('HomeController', function($scope, $http, $location, $window, FlightsService){

    $scope.doShowFlights = function(){
        if ( $scope.showFlights ){
            $scope.showFlights = false;
        } else{
            $scope.showFlights = true;
        }
    }

    $scope.getFlights = function(){
        FlightsService.getFlightsByUserEmail( "noah.williams@travel-sabre.com", $http).then( function(flights) {
            $scope.flights = flights;
        });
    }

});