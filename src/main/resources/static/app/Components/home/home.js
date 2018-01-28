'use strict'

var app = angular.module('home', ['ngRoute', 'ngAnimate', 'shared']);

app.config(function($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl : "/app/components/login/loginPage.htm",
            controller: 'HomeController'
        })
        .when("/profilePage", {
            templateUrl : "/app/components/home/profilePage.htm",
            controller: 'HomeController'
        })
        .when("/flights", {
            templateUrl : "/app/components/flights/flights.htm",
            controller: 'HomeController'
        })
        .when("/adminPage", {
            templateUrl : "/app/components/adminPanel/adminPage.htm"
        })
        .when("/addUser", {
            templateUrl : "/app/components/addUser/addUser.htm"
        })
        .when("/addFlight", {
            templateUrl : "/app/components/addFlight/addFlight.htm"
        })
        .otherwise({
            template : "/app/components/login/loginPage.htm",
            controller: 'HomeController'
        });
});