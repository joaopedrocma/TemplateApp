var keepcredService = angular.module('keepcredService', ['ngResource']);

	var baseUrl = 'http://localhost:9380'
		
		keepcredService.factory('Prestamistas', function($resource){
		    return $resource(baseUrl + '/clienteinstunidade/:id', { id: '@id' }, {
		  query:  {method: 'GET', isArray: true},
		  get:    {method: 'GET'},
		  remove: {method: 'DELETE', params: {id: '@id'}},
		  edit:   {method: 'PUT', params: {id: '@id'}},
		  add:    {method: 'POST'}
	  });	    
	});
		
	keepcredService.factory('Recusados', function($resource){
		    return $resource(baseUrl + '/recusados/:id', { id: '@id' }, {
		  query:  {method: 'GET', isArray: true},
		  get:    {method: 'GET'},
		  remove: {method: 'DELETE', params: {id: '@id'}},
		  edit:   {method: 'PUT', params: {id: '@id'}},
		  add:    {method: 'POST'}
	  });	    
	});
