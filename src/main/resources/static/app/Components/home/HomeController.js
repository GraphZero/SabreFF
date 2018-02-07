'use strict'

angular.module('home').controller('HomeController', function($scope, $http, $location, $window, $q, $timeout,
                                                             FlightsService, DataService, LoginService){

    var nextLevel = 10000;
    $scope.nextLevel = nextLevel;


    var updateData = function() {
        if (  DataService.getUser() ){
            var user = DataService.getUser();
            var neededMiles = ((Math.floor(user.initialMiles/ nextLevel) + 1 ) * nextLevel) - user.initialMiles;
            var percent = (nextLevel - neededMiles) / 100;
            $scope.name = user.firstName;
            $scope.miles = user.initialMiles;
            $scope.level = Math.floor(user.initialMiles/ nextLevel);
            $scope.neededMiles = neededMiles;
            $scope.myStyle = {
                "width": percent + "%"
            };
            FlightsService.getFlightsByUserEmail( DataService.getUser().email, $http).then( function(flights) {
                $scope.flights = flights;
            });
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


});