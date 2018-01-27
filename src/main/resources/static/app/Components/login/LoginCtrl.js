'use strict'

angular.module('home').controller('LoginController', function($scope, $http, $location, $window, LoginService ){

    $scope.login = function(){
        LoginService.login( $scope.email, $scope.password, $http).then( function(response) {
            if ( response.status == 200 ){
                console.log("Logged as: " + $scope.email + " " + $scope.password);
                $window.location.href = "/#!/profilePage";
            } else{
                console.log("Cant log in!")
                console.log("Couldn't log as: " + $scope.email + " " + $scope.password);
            }
        });
    }


});
