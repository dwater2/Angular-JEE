var app = angular.module('users', ['ngCpfCnpj','ui.mask'])

app.controller('usersCtrl', function($scope, $http) {
	$scope.action = 0;
	$scope.msg = "";
	$scope.msgOperation = "";
	$scope.msgOperationList = "";
	$scope.blnSucess = false;
	$scope.blnError = false;
	$scope.blnSucessOperation = false;
	$scope.users = [];
	$scope.roles = [];
	$scope.toFind = {};
	$scope.toCreate = {};
	
	$scope.validations = {
		phone: /^\d+$/,
		letters: /^[a-zA-Z\s]*$/
	}
	
	   $scope.profiles = {
		    model: null,
		    availableOptions: [
		      {id: '0', name: 'Aluno'},
		      {id: '1', name: 'Gestor Municipal'},
		      {id: '2', name: 'Gestor Estadual'},
		      {id: '3', name: 'Gestor Nacional'}
		    ]
		   };
	
	$http.get('/desafio/api/users').then(
		function success(response) {
			$scope.users = response.data;
		},
		function fail(error) {
			console.log(error)
		}
	);
	
	$http.get('/desafio/api/roles').then(
		function success(response) {
			$scope.roles = response.data;
		},
		function fail(error) {
			console.log(error)
		}
	);
	
	$scope.newUser = function() {
		$scope.action = 0;
		$scope.toCreate = {};
		$scope.blnSucess = false;
		$scope.blnError = false;
		$scope.blnSucessOperation = false;
		$scope.blnErrorSituation = false;
		$('.modal#new').modal('show');
	}
	
	$scope.findByFilter = function() {
		$scope.blnSucessOperation = false;
		$scope.blnErrorOperation = false;
		$http({
			url: '/desafio/api/users/find',
			method: 'POST',
			data: $scope.toFind,
			headers: {'Content-Type': 'application/json'}
		}).then(function success(response) {
			$scope.users = response.data;
		}, function fail(error) {
			$scope.users = {};
		})
		
	}
	
	$scope.editUser = function(user) {
		$scope.action = 1;
		$scope.blnSucess = false;
		$scope.blnError = false;
		$scope.blnSucessOperation = false;
		$scope.blnErrorOperation = false;
		$scope.toCreate = user;
		$('.modal#new').modal('show');
	}
	
	$scope.reset = function() {
		$scope.toCreate = {};
		$('.modal#new').modal('hide');
	}
	
	$scope.doAction = function() {
		
		switch($scope.action ) {
		    case 0:
		    	doCreate();
		        break;
		    case 1:
		    	doUpdate();
		        break;
		}
		
	}
	
	function doCreate() {
		$scope.toCreate.situation = "S";
		$http({
			url: '/desafio/api/users/new',
			method: 'POST',
			data: $scope.toCreate,
			headers: {'Content-Type': 'application/json'}
		}).then(function success(response) {
			$scope.users.push(response.data);
			$scope.msgOperation = "Cadastro efetuado com sucesso!";
			$scope.blnSucess = true;
			$scope.blnError = false;
			$scope.toCreate = {};
		}, function (response, status) {
			$scope.msgOperation = "Operação não realizada. Usuário já incluído.";
			$scope.blnError = true;
			$scope.blnSucess = false;
		})
	}
	
	$scope.deleteUser = function(user) {
		$scope.blnSucessSituation = false;
		$scope.blnErrorSituation = false;
		$scope.toDelete = user;
		$('.modal#delete').modal('show');
	}
	
	function doUpdate() {
		$http({
			url: '/desafio/api/users/update',
			method: 'PUT',
			data: $scope.toCreate,
			headers: {'Content-Type': 'application/json'}
		}).then(function success(response) {
			$scope.toCreate = {};
			$scope.msgOperation = "Alteração efetuada com sucesso!";
			$scope.blnSucess = true;
			$scope.action = 0;
		}, function fail(error) {
			console.log(error);
		})
	}
	$scope.change = function(user){
		$scope.blnSucessOperation = false;
		$scope.blnErrorOperation = false;
		$scope.toCreate = user;
	
		$http({
			url: '/desafio/api/users/update',
			method: 'PUT',
			data: $scope.toCreate,
			headers: {'Content-Type': 'application/json'}
		}).then(function success(response) {
			$scope.toCreate = {};
			$scope.msgOperationList = "Usuário "+ (user.situation == "S" ? "habilitado" : "desabilitado") + " com sucesso!";
			$scope.blnSucessOperation = true;
			$scope.toCreate = {};
		}, function fail(error) {
			$scope.blnSucessOperation = false;
			$scope.msgOperationList = "Operação não realizada.";
			$scope.blnErrorOperation = true;
		})

	}
	
	$scope.doDelete = function() {
		$http({
			url: '/desafio/api/users/delete',
			method: 'DELETE',
			data: $scope.toDelete,
			headers: {'Content-Type': 'application/json'}
		}).then(function success(response) {
			var index = getIndexById($scope.toDelete.id);
			$scope.users.splice(index, 1);
			$('.modal#delete').modal('hide');
			$scope.msgOperationList = "Exclusão efetuada com sucesso.";
			$scope.blnErrorOperation = false;
			$scope.blnSucessOperation = true;
		}, function fail(error) {
			$('.modal#delete').modal('hide');
			$scope.blnSucessOperation = false;
			$scope.msgOperationList = "Operação de exclusão não realizada.";
			$scope.blnErrorOperation = true;
		})
	}
	
	$scope.doHideModalMsg = function() {
		$scope.msg = "";
		$('.modal#msg').modal('hide');
	}
	
	function findById(id) {
		var found = {};
		for(var key in $scope.users) {
			if($scope.users[key].id === id) {
				found = $scope.users[key];
				break;
			}
		}
		return found;
	}
	
	function getIndexById(id) {
		var index = -1;
		var count = 0;
		for(var key in $scope.users) {
			if($scope.users[key].id === id) {
				index = count;
				break;
			}
			count++;
		}
		return index;
	}

})