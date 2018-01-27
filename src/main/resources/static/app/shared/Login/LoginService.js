'use strict'


angular.module('shared').service( 'LoginService', function() {

    this.login = function( email, name, $http ) {
        var absUrl = "/testLogin/" + email + "?name=" + name ;
        return $http( {
            method: 'POST',
            url: absUrl,
            accept: "application/json"
        }).then(
            function successCallback(response) {
                return response;
        }, function errorCallback(response) {
                return response;
        });
    };


});