var keepcred = angular.module('keepcred', [ 'ngRoute', 'keepcredService' ]);

// configure our routes
keepcred.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	.when('/signup', {
		templateUrl : 'pages/signup.html',
		controller : 'signupController'
	})

	.when('/import', {
		templateUrl : 'pages/import.html',
		controller : 'fileUploadController'
	})

	.when('/importfederais', {
		templateUrl : 'pages/importfederais.html',
		controller : 'uploadFederaisController'
	})

	.when('/importfundacoes', {
		templateUrl : 'pages/importfundacoes.html',
		controller : 'uploadFundacoesController'
	})

	.when('/prestamistas', {
		templateUrl : 'pages/prestamistas.html',
		controller : 'prestamistasController'
	})

	.when('/prestamistaslist', {
		templateUrl : 'pages/prestamistaslist.html',
		controller : 'prestamistasListController'
	})

	.when('/recusadoslist', {
		templateUrl : 'pages/recusadoslist.html',
		controller : 'recusadosListController'
	})

	.when('/recusados-add/:id', {
		templateUrl : 'pages/recusados-add.html',
		controller : 'prestamistasListController'
	})

	.when('/recusados-edit/:id', {
		templateUrl : 'pages/recusados-edit.html',
		controller : 'recusadosListController'
	})
});