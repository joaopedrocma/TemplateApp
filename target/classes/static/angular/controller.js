template.controller('mainController', function($scope) {
});

template
		.controller(
				'usersController',
				[
						'$scope',
						'$http',
						'$location',
						'$routeParams',
						'Users',
						'UserRoles',
						'Groups',
						'GroupRoles',
						function($scope, $http, $location, $routeParams, Users,
								UserRoles, Groups, GroupRoles) {
							$scope.curPage = 0;
							$scope.pageSize = 6;

							$scope.usersList = Users.query();
							$scope.userRolesList = UserRoles.query();
							$scope.groupsList = Groups.query();
							$scope.groupRolesList = GroupRoles.query();

							$scope.boolToStr = function(arg) {
								return arg ? 'Sim' : 'Não'
							};

							$scope.refresh = function() {
								$scope.usersList = Users.query();
								$scope.userRolesList = UserRoles.query();
								$scope.groupsList = Groups.query();
								$scope.groupRolesList = GroupRoles.query();
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

								$scope.errorMessage = 'Ocorreu um erro na remoção do usuário!';
								$scope.successMessage = 'Usuário removido com sucesso!';

								$scope.refresh();

								$location.path('/user_list/');
							};

							$scope.register = function() {
								$scope.errorMessage = 'Ocorreu um erro no registro do usuário!';
								$scope.successMessage = 'Usuário registrado com sucesso!';

								$scope.newUser.enabled = 1;
								Users.save($scope.newUser,

								function(data) {
									$scope.refresh();

									$scope.reset();

									$location.path('/user_list/');
								});
							};

							$scope.update = function() {
								$scope.errorMessage = 'Ocorreu um erro na atualização do usuário!';
								$scope.successMessage = 'Usuário atualizado com sucesso!';

								Users.edit($scope.updateUser, function(data) {
									$scope.refresh();

									$scope.reset();

									$location.path('/user_list/');
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

angular.module('template').filter('pagination', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	};
});

template.directive('fileModel', [ '$parse', function($parse) {
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

template.directive('lowerCase', function($parse) {
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

template.directive('upperCase', function($parse) {
	return {
		require : 'ngModel',
		link : function postLink(scope, element, attrs, modelCtrl) {
			var upperize = function(inputValue) {
				if (!inputValue) {
					return inputValue;
				}
				var upperize = inputValue.toUpperCase();
				if (upperize !== inputValue) {
					modelCtrl.$setViewValue(upperize);
					modelCtrl.$render();
				}
				return upperize;
			};

			var model = $parse(attrs.ngModel);
			modelCtrl.$parsers.push(upperize);
			upperize(model(scope));
		}
	};
});

function InvalidMsg(textbox) {
	if (textbox.value == '') {
		textbox.setCustomValidity('Campo obrigatório!');
	} else {
		textbox.setCustomValidity('');
	}
	return true;
}