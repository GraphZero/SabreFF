'use strict'

angular.module('home').controller('HomeController', function($scope, $http, $location, $window, $q, $timeout,
                                                             FlightsService, DataService, LoginService){



    var updateData = function() {
        if (  DataService.getUser() ){
            $scope.name = DataService.getUser().firstName;
        } else{
            $timeout(updateData, 1000);
        }
    };

    $timeout(updateData, 100);

    $scope.login = function(){
        LoginService.login( $scope.email, $scope.password, $http, $q).then( function(response) {
            if ( response.status == 200 ){
                $scope.name = response.data.firstName;
                DataService
                    .getUserData($scope.email, $http)
                    .then(function successCallback() {
                        $location.path( '/profilePage' );
                    }, function errorCallback() {
                    });
            } else{
                console.log("Couldn't login as: " + $scope.email + " " + $scope.password);
            }
        });
    };

    $scope.doShowFlights = function(){
        if ( $scope.showFlights ){
            $scope.showFlights = false;
        } else{
            $scope.showFlights = true;
        }
    };

    $scope.getFlights = function(){
        FlightsService.getFlightsByUserEmail( DataService.getUser().email, $http).then( function(flights) {
            $scope.flights = flights;
        });
    }


});