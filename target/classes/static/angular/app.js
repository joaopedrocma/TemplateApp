var keepcred = angular.module('keepcred', [ 'ngRoute', 'keepcredService' ]);

// configure our routes
keepcred.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	.when('/import', {
		templateUrl : 'pages/import.html',
		controller : 'fileUploadController'
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
		controller : 'prestamistasController'
	})

	.when('/prestamistas_list', {
		templateUrl : 'pages/prestamistas_list.html',
		controller : 'prestamistasListController'
	})

	.when('/prestamistas_recusados_list', {
		templateUrl : 'pages/prestamistas_recusados_list.html',
		controller : 'recusadosListController'
	})

	.when('/user_list', {
		templateUrl : 'pages/user_list.html',
		controller : 'userController'
	})

	.when('/user_add', {
		templateUrl : 'pages/user_add.html',
		controller : 'userController'
	})

	.when('/user_edit', {
		templateUrl : 'pages/user_edit.html',
		controller : 'userController'
	})

	.when('/prestamistas_recusados_add/:id', {
		templateUrl : 'pages/prestamistas_recusados_add.html',
		controller : 'prestamistasListController'
	})

	.when('/prestamistas_recusados_edit/:id', {
		templateUrl : 'pages/prestamistas_recusados_edit.html',
		controller : 'recusadosListController'
	})
});