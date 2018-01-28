'use strict'


angular.module('shared').service( 'LoginService', function() {
    var user;

    this.login = function( email, name, $http, $q ) {
        var absUrl = "/testLogin/" + email + "?name=" + name ;
        var defer = $q.defer();
        return $http( {
            method: 'GET',
            url: absUrl,
            accept: "application/json"
        }).then(
            function successCallback(response) {
                user = response.data;
                defer.resolve(response);
                return defer.promise;
        }, function errorCallback(response) {
                return response;
        });
    };

    this.getUser = function(){
        return user;
    }

});