'use strict';
angular.module('issueTracker')
	.directive('header',function(){
		var headerController = ['$scope','HTTPService','$uibModal', function ($scope, HTTPService, $uibModal) {
			$scope.changePassword = function() {
	        	var modalInstance = $uibModal.open({
	                controller: 'PasswordCtrl',
	                templateUrl: 'pages/changePassword.html',
	                size : 'md',
	                scope: $scope
	           });
/*	        	$scope.changePassword.result.then(function (result) {
	                console.log("Password modal Result ::  "+result);
	            });*/
	        };
	      }];
		return {
	        restrict: 'E',
	        replace: true,
	        controller: headerController,
            templateUrl:'directive/header/header.html'
    	}
	});


