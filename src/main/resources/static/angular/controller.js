keepcred.controller('mainController', function($scope) {
});

keepcred
.controller(
		'userController',
		[
				'$scope',
				'$http',
				'$location',
				'$routeParams',
				'Users',
				'UserRoles',
				'Groups',
				'GroupRoles',
				function($scope, $http, $location, $routeParams, Users, UserRoles, Groups, GroupRoles) {					
					$scope.curPage = 0;
					$scope.pageSize = 6;

					$scope.titlelist = 'Usuários';
					$scope.titleAdd = 'Cadastrar Usuário';
					$scope.titleEdit = 'Editar Usuário';

					$scope.usersList = Users.query();
					$scope.userRolesList = UserRoles.query();
					$scope.groupsList = Groups.query();
					$scope.groupRolesList = GroupRoles.query();
					
					$scope.refresh = function() {
						$scope.usersList = Users.query();
					};

					$scope.init = function() {
						$scope.updateUser = Users.get({
							id : $routeParams.id
						});
					};

					$scope.reset = function() {
						$scope.newUser = {};
					};

					$scope.editUser = function(userId) {
						$location.path('/user_edit/' + userId);
					};

					$scope.deleteUser = function(userId) {
						Users.remove({
							id : userId
						});

						$scope.refresh();

						$location.path('/user_list/');
					};

					$scope.register = function() {					
						$scope.errorMessage = 'Ocorreu um erro no registro do usuário!';
						
						$scope.newUser.enabled = 1;
						Users.save($scope.newUser,
							function(data) {
								$scope.successMessage = 'Usuário registrado com sucesso!';

								$scope.refresh();

								$scope.reset();
							});
					};

					$scope.update = function() {
						
						$scope.errorMessage = 'Ocorreu um erro na atualização do usuário!';

						Users.edit($scope.updateUser, function(
								data) {

							$location.path('/user_list/');

							$scope.refresh();

							$scope.reset();
						});
					};

					$scope.refresh();

					$scope.reset();

					$scope.orderBy = 'users';

					$scope.numberOfPages = function() {
						return Math.ceil($scope.usersList.length
								/ $scope.pageSize);
					}
				} ]);

keepcred.controller('prestamistasController', ['$http', '$scope', function($http, $scope) {	
	$scope.loading = false;
	$scope.getPrestamistas = function() {
		$scope.loading = true;
		$http({
			  method: 'POST',
			  url: '/prestamistas'
		}).success(function() {
			alert("Arquivo criado com sucesso");
		}).error(function() {
			alert("Ocorreu um erro na criação do arquivo!");
		}).finally(function () {
		      $scope.loading = false;
		});
	}
} ]);

keepcred
.controller(
		'prestamistasListController',
		[
				'$scope',
				'$http',
				'$location',
				'$routeParams',
				'Prestamistas',
				'Recusados',
				function($scope, $http, $location, $routeParams, Prestamistas, Recusados) {				
					$scope.curPage = 0;
					$scope.pageSize = 6;

					$scope.statusTypes = [ {
						id : 'RECUSADO',
						name : 'Recusado'
					}, {
						id : 'EXCLUIDO',
						name : 'Excluido'
					}];
					
					$scope.titlelist = 'Prestamistas';
					$scope.titleadd = 'Adicionar Recusado';

					$scope.prestamistasList = Prestamistas.query();

					$scope.refresh = function() {
						$scope.prestamistasList = Prestamistas.query();
					};

					$scope.init = function() {
						$scope.newRecusado = Prestamistas.get({
							id : $routeParams.id
						});
					};
					
					$scope.addPrestamista = function(prestamistaId) {
						$location.path('/prestamistas_recusados_add/' + prestamistaId);
					};
					
					$scope.register = function() {
						$scope.errorMessage = 'Ocorreu um erro no registro do recusado';

						Recusados.save($scope.newRecusado,function(data) {

											$scope.successMessage = 'Recusado Registrado com Sucesso!';

											$location.path('/prestamistas_list/');
											
											$scope.refresh();
										});
					};
					
					$scope.orderBy = 'descnomecliente';

					$scope.numberOfPages = function() {
						return Math
								.ceil($scope.prestamistaList.length
										/ $scope.pageSize);
					}
				} ]);


keepcred
.controller(
		'recusadosListController',
		[
				'$scope',
				'$http',
				'$location',
				'$routeParams',
				'Prestamistas',
				'Recusados',
				function($scope, $http, $location, $routeParams, Prestamistas, Recusados) {
					$scope.curPage = 0;
					$scope.pageSize = 6;

					$scope.statusTypes = [ {
						id : 'RECUSADO',
						name : 'Recusado'
					}, {
						id : 'EXCLUIDO',
						name : 'Excluido'
					}];
					
					$scope.titlelist = 'Recusados';
					
					$scope.recusadosList = Recusados.query();

					$scope.refresh = function() {
						$scope.recusadosList = Recusados.query();
					};

					$scope.init = function() {
						$scope.updateRecusado = Recusados.get({
							id : $routeParams.id
						});
					};

					$scope.editRecusado = function(recusadoId) {
						$location.path('/prestamistas_recusados_edit/'
								+ recusadoId);
					};

					$scope.deleteRecusado = function(
							recusadoId) {
						Recusados.remove({
							id : recusadoId
						});

						$scope.refresh();

						$location.path('/prestamistas_recusados_list/');

					};

					$scope.update = function() {
						$scope.errorMessages = '';
						$scope.errors = {};

						Recusados.edit($scope.updateRecusado, function(data) {

							$location.path('/prestamistas_recusados_list/');

							$scope.refresh();
						});
					};

					$scope.refresh();

					$scope.orderBy = 'descnomecliente';

					$scope.numberOfPages = function() {
						return Math
								.ceil($scope.recusadosList.length
										/ $scope.pageSize);
					}
				} ]);



keepcred.controller('fileUploadController', [ '$http', '$scope',
		function($http, $scope) {
	$scope.loading = false;
			$scope.fileTypes = [ {
				id : 'FILE_TYPE_FAEPU_FARMACIA',
				name : 'FAEPU_FARMACIA'
			}, {
				id : 'FILE_TYPE_UFU_FARMACIA',
				name : 'UFU_FARMACIA'
			}, {
				id : 'FILE_TYPE_FAEPU_SALARIOS',
				name : 'FAEPU_SALARIOS'
			}, {
				id : 'FILE_TYPE_UFU_SALARIOS',
				name : 'UFU_SALARIOS'
			} ];

			$scope.uploadFile = function() {
				$scope.loading = true;
				var file = $scope.myFile;
				var fileType = $scope.fileType;
				var uploadUrl = "/upload";
				var dateTime = $scope.dateTime;
				var data = new FormData();

				data.append('file', file);
				data.append('fileType', fileType);
				data.append('dateTime', dateTime);

				$http.post(uploadUrl, data, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}			
				}).success(function(data) {
					alert("Arquivo criado com sucesso");
					
				}).error(function(data) {
					alert("Erro!");
				}).finally(function () {
				      $scope.loading = false;
				});
			}
		
		} ]);

keepcred.controller('uploadFederaisController', [ '$http', '$scope',
                                       		function($http, $scope) {
                                       	$scope.loading = false;

                                       			$scope.uploadFederais = function() {
                                       				$scope.loading = true;
                                       				var fileServidor = $scope.fileServ;
                                       				var filePensionista = $scope.filePens;
                                       				var fileCapitalAgu = $scope.fileCapAgu;
                                       				var fileCapitalDpf = $scope.fileCapDpf;
                                       				var fileCapitalIftm = $scope.fileCapIftm;
                                       				var fileCapitalMec = $scope.fileCapMec;
                                       				var fileCapitalMtb = $scope.fileCapMtb;
                                       				var fileCapitalUfu = $scope.fileCapUfu;
                                       				var fileEmprestimoAgu = $scope.fileEmpAgu;
                                       				var fileEmprestimoDpf = $scope.fileEmpDpf;
                                       				var fileEmprestimoIftm = $scope.fileEmpIftm;
                                       				var fileEmprestimoMec = $scope.fileEmpMec;
                                       				var fileEmprestimoMtb = $scope.fileEmpMtb;
                                       				var fileEmprestimoUfu = $scope.fileEmpUfu;
                                       				var uploadUrl = "/uploadfederais";
                                       				var data = new FormData();

                                       				data.append('fileServidor', fileServidor);
                                       				data.append('filePensionista', filePensionista);
                                       				data.append('fileCapitalAgu', fileCapitalAgu);
                                       				data.append('fileCapitalDpf', fileCapitalDpf);
                                       				data.append('fileCapitalIftm', fileCapitalIftm);
                                       				data.append('fileCapitalMec', fileCapitalMec);
                                       				data.append('fileCapitalMtb', fileCapitalMtb);
                                       				data.append('fileCapitalUfu', fileCapitalUfu);
                                       				data.append('fileEmprestimoAgu', fileEmprestimoAgu);
                                       				data.append('fileEmprestimoDpf', fileEmprestimoDpf);
                                       				data.append('fileEmprestimoIftm', fileEmprestimoIftm);
                                       				data.append('fileEmprestimoMec', fileEmprestimoMec);
                                       				data.append('fileEmprestimoMtb', fileEmprestimoMtb);
                                       				data.append('fileEmprestimoUfu', fileEmprestimoUfu);

                                       				$http.post(uploadUrl, data, {
                                       					transformRequest : angular.identity,
                                       					headers : {
                                       						'Content-Type' : undefined
                                       					}			
                                       				}).success(function(data) {
                                       					alert("Arquivo criado com sucesso");
                                       				}).error(function(data) {
                                       					alert("Erro!");
                                       				}).finally(function () {
                                       				      $scope.loading = false;
                                       				});
                                       			}
                                       			
                                       		} ]);

keepcred.controller('uploadFundacoesController', [ '$http', '$scope',
                                       		function($http, $scope) {
                                       	$scope.loading = false;
                                       			$scope.fileTypes = [ {
                                       				id : 'FILE_TYPE_FAEPU',
                                       				name : 'FAEPU'
                                       			}, {
                                       				id : 'FILE_TYPE_FAU',
                                       				name : 'FAU'
                                       			}, {
                                       				id : 'FILE_TYPE_FUNDAP',
                                       				name : 'FUNDAP'
                                       			},{
                                       				id : 'FILE_TYPE_RTU',
                                       				name : 'RTU'
                                       			},{
                                       				id : 'FILE_TYPE_SINTET',
                                       				name : 'SINTET'
                                       			}];

                                       			$scope.uploadFundacoes = function() {
                                       				$scope.loading = true;
                                       				var filecap = $scope.myFileCap;
                                       				var fileemp = $scope.myFileEmp;
                                       				var fileType = $scope.fileType;
                                       				var uploadUrl = "/uploadfundacoes";
                                       				var data = new FormData();

                                       				data.append('filecap', filecap);
                                       				data.append('fileemp', fileemp);
                                       				data.append('fileType', fileType);

                                       				$http.post(uploadUrl, data, {
                                       					transformRequest : angular.identity,
                                       					headers : {
                                       						'Content-Type' : undefined
                                       					}			
                                       				}).success(function(data) {
                                       					alert("Arquivo criado com sucesso");
                                       				}).error(function(data) {
                                       					alert("Erro!");
                                       				}).finally(function () {
                                       				      $scope.loading = false;
                                       				});
                                       			}
                                       		
                                       		} ]);

angular.module('keepcred').filter('pagination', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	};
});

keepcred.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

keepcred.directive('lowerCase', function($parse) {
	return {
		require : 'ngModel',
		link : function postLink(scope, element, attrs, modelCtrl) {
			var lowerize = function(inputValue) {
				if (!inputValue) {
					return inputValue;
				}
				var lowerized = inputValue.toLowerCase();
				if (lowerized !== inputValue) {
					modelCtrl.$setViewValue(lowerized);
					modelCtrl.$render();
				}
				return lowerized;
			};

			var model = $parse(attrs.ngModel);
			modelCtrl.$parsers.push(lowerize);
			lowerize(model(scope));
		}
	};
});