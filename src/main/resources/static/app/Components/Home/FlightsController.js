'use strict'
/**
 * Created by Andrzej on 2017-11-30.
 */

angular.module('home').controller('FlightsController', function($scope, $http, $location, $window, FlightsService){

    FlightsService.getFlightsByUserEmail( "noah.williams@travel-sabre.com", $http).then( function(flights) {
        $scope.flights = flights;
    });

    $scope.doShowFlights = function(){
        if ( $scope.showFlights ){
            $scope.showFlights = false;
        } else{
            $scope.showFlights = true;
        }
    }

});