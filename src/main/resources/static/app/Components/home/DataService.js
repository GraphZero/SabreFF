'use strict'


angular.module('home').service( 'DataService', function() {
    var user;

    this.getUserData = function(email, $http){
        var absUrl = "/getUser/" + email;
        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
        }).then(
            function successCallback(response) {
                console.log(response.data);
                user = response.data;
                return response;
            }, function errorCallback() {
                console.log("What the hell...?");
            });
    };

    this.getUser = function(){
        return user;
    }

});