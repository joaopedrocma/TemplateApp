var templateService = angular.module('templateService', ['ngResource']);

	var baseUrl = 'http://localhost:9380'
		
		templateService.factory('Users', function($resource){
	    return $resource(baseUrl + '/users/:id', { id: '@id' }, {
	  query:  {method: 'GET', isArray: true},
	  get:    {method: 'GET'},
	  remove: {method: 'DELETE', params: {id: '@id'}},
	  edit:   {method: 'PUT', params: {id: '@id'}},
	  add:    {method: 'POST'}
  });	    
});
	
	templateService.factory('UserRoles', function($resource){
	    return $resource(baseUrl + '/userRoles/:id', { id: '@id' }, {
	  query:  {method: 'GET', isArray: true},
	  get:    {method: 'GET'},
	  remove: {method: 'DELETE', params: {id: '@id'}},
	  edit:   {method: 'PUT', params: {id: '@id'}},
	  add:    {method: 'POST'}
  });	    
});
	
	templateService.factory('Groups', function($resource){
	    return $resource(baseUrl + '/groups/:id', { id: '@id' }, {
	  query:  {method: 'GET', isArray: true},
	  get:    {method: 'GET'},
	  remove: {method: 'DELETE', params: {id: '@id'}},
	  edit:   {method: 'PUT', params: {id: '@id'}},
	  add:    {method: 'POST'}
  });	    
});
	
	templateService.factory('GroupRoles', function($resource){
	    return $resource(baseUrl + '/groupRoles/:id', { id: '@id' }, {
	  query:  {method: 'GET', isArray: true},
	  get:    {method: 'GET'},
	  remove: {method: 'DELETE', params: {id: '@id'}},
	  edit:   {method: 'PUT', params: {id: '@id'}},
	  add:    {method: 'POST'}
  });	    
});