'use strict'

var app = angular.module('home', ['ngRoute', 'ngAnimate', 'shared']);

app.config(function($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl : "/app/components/login/loginPage.htm"
        })
        .when("/profilePage", {
            templateUrl : "/app/components/home/profilePage.htm"
        })
        .otherwise({
            template : "/app/components/login/loginPage.htm"
        });
});