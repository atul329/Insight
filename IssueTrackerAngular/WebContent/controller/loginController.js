(function () {
    'use strict';

    angular
        .module('issueTracker')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$scope','$state', 'AuthenticationService'];
    function LoginController($scope, $state, AuthenticationService) {
        //var vm = this;
    	$scope.pageClass = 'page-login';
        $scope.login = login;
        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login(user) {
        	$scope.dataLoading = true;
            AuthenticationService.Login(user.username, user.password).then(function (response){
                if (response.success) {
                	console.log("Login Successfull :: "+response.data.empName);
                    AuthenticationService.SetCredentials(user.username, user.password, response.data);
                   $state.transitionTo('tracker.dashboard');
                } else {
                	console.log("Response.success :: "+response.success);
                	console.log("Data is :: "+response.data);
                	console.log('Inside Failure');
                	$scope.message='Username/Password is incorrect  ';
                	$scope.dataLoading = false;
                	$state.transitionTo('login');
                    /*FlashService.Error(response.message);
                    vm.dataLoading = false;*/
                }
            });
        };
    }

})();
