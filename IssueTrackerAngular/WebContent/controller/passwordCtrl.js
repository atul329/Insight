(function() {
 'use strict';
 angular.module('issueTracker').controller('PasswordCtrl', PasswordCtrl);
 PasswordCtrl.$inject = ['$scope', '$uibModalInstance', 'HTTPService', '$cookies','ngDialog'];
 function PasswordCtrl($scope, $uibModalInstance, HTTPService, $cookies,ngDialog) {
     var currentUser = $cookies.get('currentUser');
     var userData = currentUser?JSON.parse(currentUser):[];
     $scope.message = "New Password and Confirm password should match !!";
     $scope.changePassword = function(user){
    	 console.log('Inside form Submit !!');
   	  $scope.formData = angular.copy(user);
   	  if($scope.formData.newPassword === $scope.formData.confirmPassword){
   		HTTPService.changePassword($scope.formData, userData.loginId).then(function(response){
     		  if(!response.data){
     			  $scope.message = "Entered old password is wrong !!";
     			  //$uibModalInstance.dismiss(response.data);
     			  ngDialog.open({ 
     				  template: 'templateId', 
     				  scope:$scope,
     				  className: 'ngdialog-theme-default'
     				});
     		  }else {
     			 $scope.message = "Password changed successfully";
    			 //$uibModalInstance.dismiss(response.data);
    			  ngDialog.open({ 
     				  template: 'templateId', 
     				  scope:$scope,
     				  className: 'ngdialog-theme-default'
     			});
     		  }
     	  });
   	  }else{
   		ngDialog.open({ 
			  template: 'templateId', 
			  scope:$scope,
			  className: 'ngdialog-theme-default'
		});
   	  }
     };
     $scope.cancel = function () {
         $scope.selectedRow = null;
         $uibModalInstance.dismiss('cancel');
     };	
 }

})();