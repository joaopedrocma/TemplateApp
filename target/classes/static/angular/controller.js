keepcred.controller('mainController', function($scope) {
});

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
					
					$scope.title = 'Prestamistas';
					// $scope.titleAdd = 'Novo Bairro';
					// $scope.titleEdit = 'Editar Bairro';
					$scope.message = 'Adicionar Recusado';

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
						$location.path('/recusados-add/' + prestamistaId);
					};
					
					$scope.register = function() {
						$scope.errorMessages = '';
						$scope.errors = {};

						Recusados
								.save(
										$scope.newRecusado,
										function(data) {

											$scope.successMessage = 'Recusado Registrado com Sucesso!';

											$location.path('/prestamistaslist/');
											
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
					
					$scope.title = 'Recusados';
					// $scope.titleEdit = 'Editar Recusado';

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
						$location.path('/recusados-edit/'
								+ recusadoId);
					};

					$scope.deleteRecusado = function(
							recusadoId) {
						Recusados.remove({
							id : recusadoId
						});

						$scope.refresh();

						$location.path('/recusadoslist/');

					};

					$scope.update = function() {
						$scope.errorMessages = '';
						$scope.errors = {};

						Recusados.edit($scope.updateRecusado, function(data) {

							$location.path('/recusadoslist/');

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
			alert("Erro!");
		}).finally(function () {
		      $scope.loading = false;
		});
	}
} ]);

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