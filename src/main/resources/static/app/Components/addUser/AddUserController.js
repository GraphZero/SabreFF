'use strict'

angular.module('home').controller('AddUserCtrl', function($scope, $http){

    $scope.addUser = function(){
        var absUrl = "/addUser/";
        var user = {
            firstName: $scope.firstName,
            lastName: $scope.lastName,
            email: $scope.email,
            initialMiles: $scope.initialMiles,
            birthDate: $scope.birthDate,
            username: $scope.username,
            password: $scope.password
        };
        var config = {
            method: 'POST',
            url: absUrl,
            accept: "application/json"
        };
        return $http.post( absUrl, user, config)
            .then(
                function(response){
                    console.log("Successfully added user.");
                },
                function(response){
                    console.log("Couldnt add user." + response);
                });
    }


});
