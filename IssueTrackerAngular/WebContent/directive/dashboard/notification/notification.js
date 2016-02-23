'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('issueTracker')
	.directive('notifications',function(){
		var controller = ['$scope','HTTPService', function ($scope, HTTPService) {

			$scope.oneAtATime = true;
			  $scope.status = {
					    isFirstOpen: true,
					    isFirstDisabled: false
					  };
	      	HTTPService.getLatestIssueUpdates().then(function(response){
	    		$scope.latestIssueList=response.data;
	    	});
	      }];
		return {
        templateUrl:'directive/dashboard/notification/notification.html',
        restrict: 'E',
        scope: {},
        controller : controller,
        replace: true,
    	}
	});


