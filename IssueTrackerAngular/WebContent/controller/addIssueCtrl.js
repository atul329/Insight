(function() {
 'use strict';

 angular.module('issueTracker').controller('AddIssueCtrl', AddIssueCtrl);
 AddIssueCtrl.$inject = [ '$state', 'AuthenticationService', '$scope',
   'SelectHelper','HTTPService', '$cookies', 'ngDialog'];
 function AddIssueCtrl($state, AuthenticationService, $scope, SelectHelper,HTTPService, $cookies, ngDialog) {

  var date = new Date();
  $scope.minDate = date.setDate((new Date()).getDate() - 30);
  $scope.maxDate = new Date();
  $scope.initDate = new Date();
  $scope.open1 = function() {
   $scope.popup1.opened = true;
  };
  $scope.popup1 = {
   opened : false
  };
  $scope.status1 = SelectHelper.getPartialStatus();
  $scope.reportedBy1 = SelectHelper.getTeamforAddIssue();
  $scope.tracker = SelectHelper.getTracker();
  $scope.priority = SelectHelper.getPriority();
  var currentUser = $cookies.get('currentUser');
  var userData = currentUser?JSON.parse(currentUser):[]; 
  $scope.username = userData.username;
  console.log("userName is :: "+$scope.username);
  $scope.flag = false;
  $scope.message = "Issue Added Successfully !!";
  $scope.addIssue = function(user){
	  $scope.formData = angular.copy(user);
	  HTTPService.addIssue($scope.formData, $scope.username).then(function(response){
		  console.log("Response :: "+response);
		  console.log("Response.data :: "+response.data);
		  console.log("Response.success :: "+response.success);
		  $scope.flag = response.success;
		  if(!response.data){
			  $scope.message = "There was some error adding the issue please try again after some time !!";
			  ngDialog.open({ 
 				  template: 'templateId',
 				  scope:$scope,
 				  className: 'ngdialog-theme-default'
 			});
		  }
		  else{
			  ngDialog.open({ 
 				  template: 'templateId',
 				  scope:$scope,
 				  className: 'ngdialog-theme-default'
 			});
		  }
	  });
  };
  
 }
})();