var keepcred = angular.module('keepcred', [ 'ngRoute', 'keepcredService' ]);

// configure our routes
keepcred.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	.when('/import_folha_pagamento', {
		templateUrl : 'pages/import_folha_pagamento.html',
		controller : 'uploadFolhaPagamentoController'
	})

	.when('/import_federais', {
		templateUrl : 'pages/import_federais.html',
		controller : 'uploadFederaisController'
	})

	.when('/import_fundacoes', {
		templateUrl : 'pages/import_fundacoes.html',
		controller : 'uploadFundacoesController'
	})

	.when('/prestamistas', {
		templateUrl : 'pages/prestamistas.html',
		controller : 'generatePrestamistasController'
	})

	.when('/prestamistas_list', {
		templateUrl : 'pages/prestamistas_list.html',
		controller : 'prestamistasController'
	})

		.when('/prestamistas_recusados_add/:id', {
		templateUrl : 'pages/prestamistas_recusados_add.html',
		controller : 'prestamistasController'
	})
	
	.when('/prestamistas_recusados_list', {
		templateUrl : 'pages/prestamistas_recusados_list.html',
		controller : 'prestamistasRecusadosController'
	})

	.when('/prestamistas_recusados_edit/:id', {
		templateUrl : 'pages/prestamistas_recusados_edit.html',
		controller : 'prestamistasRecusadosController'
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