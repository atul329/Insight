(function () {
    'use strict';
    
angular.module('issueTracker').controller('UpdateController',
	    		['$scope', '$uibModalInstance', 'selectedRow', 'HTTPService', 'SelectHelper', '$cookies', 'ngDialog',
	    		function($scope, $uibModalInstance, selectedRow, HTTPService, SelectHelper,$cookies, ngDialog){
	    	        $scope.selectedRow = selectedRow;
	    	        $scope.status = SelectHelper.getUpdateStatus();
	    	        console.log('UpdateController :: '+selectedRow.issueId);
	    	        var date = new Date();
	    	        $scope.minDate = date.setDate((new Date()).getDate() - 30);
	    	        $scope.maxDate = new Date();
	    	        $scope.open1 = function() {
	    	         $scope.popup1.opened = true;
	    	        };
	    	        $scope.popup1 = {
	    	         opened : false
	    	        };
	    	        var currentUser = $cookies.get('currentUser');
	    	        var userData = currentUser?JSON.parse(currentUser):[];
	    	        $scope.status = SelectHelper.getUpdateStatus();
	    	        $scope.flag = false;
	    	        $scope.classFlag = true;
	    	        $scope.updateInfo = function(user){
	    	      	  $scope.formData = angular.copy(user);
	    	      	  HTTPService.updateIssue($scope.formData, userData.username,$scope.selectedRow).then(function(response){
	    	      		  $scope.flag = response.success;
	    	      		  if(!response.data){
	    	      			  $scope.message = "There was some error adding the issue please try again after some time !!";
	    	      			  $scope.flag = true;
	    	    	          $scope.classFlag = false;
	    	     			  ngDialog.open({ 
	    	     				  template: 'updateResponse', 
	    	     				  scope:$scope,
	    	     				  className: 'ngdialog-theme-default'
	    	     				});
	    	      		  }else {
	    	      			$scope.message = "Issue Update Successfully !!";
	    	      			$scope.flag = true;
	    	    	        $scope.classFlag = true;
	    	     			ngDialog.open({ 
	    	     				  template: 'updateResponse', 
	    	     				  scope:$scope,
	    	     				  className: 'ngdialog-theme-default'
	    	     				});
	    	      		  }
	    	      	  });
	    	        };
	    	        $scope.cancel = function () {
	    	            $scope.selectedRow = null;
	    	            $uibModalInstance.close();
	    	        };	
	    	    		
	    		}
	    ]);
})();