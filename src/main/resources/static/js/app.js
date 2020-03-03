'use strict';

angular.module('app', ['ngRoute', 'ngResource'])
.config(function ($routeProvider) {
	$routeProvider
	.when('/touristlist', {
		templateUrl: 'partials/touristlist.html',
		controller: 'TouristListController',
		controllerAs: 'tListCtrl'
	})
	.when('/touristdetails/:id', {
		templateUrl: 'partials/touristdetails.html',
		controller: 'TouristDetailsController',
		controllerAs: 'tDetailsCtrl'
	})
	.when('/newtourist', {
		templateUrl: 'partials/newtourist.html',
		controller: 'TouristNewController',
		controllerAs: 'tNewCtrl'
	})
	.when('/flightlist', {
		templateUrl: 'partials/flightlist.html',
		controller: 'FlightListController',
		controllerAs: 'fListCtrl'
	})
	.when('/flightdetails/:id', {
		templateUrl: 'partials/flightdetails.html',
		controller: 'FlightDetailsController',
		controllerAs: 'fDetailsCtrl'
	})
	.when('/newflight', {
		templateUrl: 'partials/newflight.html',
		controller: 'FlightNewController',
		controllerAs: 'fNewCtrl'
	})
	.otherwise({
		redirectTo: '/flightlist'
	});
})
.constant('TOURIST_ENDPOINT', 'api/tourists/:id')
.factory('Tourist', function($resource, TOURIST_ENDPOINT) {
	return $resource(TOURIST_ENDPOINT);
})
.constant('FLIGHT_ENDPOINT', 'api/flights/:id')
.factory('Flight', function($resource, FLIGHT_ENDPOINT) {
	return $resource(FLIGHT_ENDPOINT);
})
.service('Tourists', function(Tourist) {
	this.getAll = function() {
		return Tourist.query();
	}
	this.get = function(touristindex) {
		return Tourist.get({id: touristindex});
	}
	this.add = function(tourist) {
		tourist.$save();
	}
})
.service('Flights', function(Flight) {
	this.getAll = function() {
		return Flight.query();
	}
	this.get = function(flightindex) {
		return Flight.get({id: flightindex});
	}
	this.add = function(flight) {
		flight.$save();
	}
})
.controller('TouristListController', function(Tourists) {
	var vm = this;
	vm.tourists = Tourists.getAll();
})
.controller('TouristDetailsController', function($routeParams, Tourists) {
	var vm = this;
	var touristIndex = $routeParams.id;
	vm.tourist = Tourists.get(touristIndex);
})
.controller('TouristNewController', function(Tourists, Tourist) {
	var vm = this;
	vm.tourist = new Tourist();
	vm.saveTourist = function() {
		Tourists.add(vm.tourist);
		vm.tourist = new Tourist();
	}
})
.controller('FlightListController', function(Flights) {
	var vm = this;
	vm.flights = Flights.getAll();
})
.controller('FlightDetailsController', function($routeParams, Flights) {
	var vm = this;
	var flightIndex = $routeParams.id;
	vm.flight = Flights.get(flightIndex);
})
.controller('FlightNewController', function(Flights, Flight) {
	var vm = this;
	vm.flight = new Flight();
	vm.saveFlight = function() {
		Flights.add(vm.flight);
		vm.flight = new Flight();
	}
});