var template = angular.module('template', [ 'ngRoute', 'templateService' ]);

// configure our routes
template.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})
	
	.when('/user_list', {
		templateUrl : 'pages/user_list.html',
		controller : 'usersController'
	})

	.when('/user_add', {
		templateUrl : 'pages/user_add.html',
		controller : 'usersController'
	})

	.when('/user_edit/:id', {
		templateUrl : 'pages/user_edit.html',
		controller : 'usersController'
	})
});