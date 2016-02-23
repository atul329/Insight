(function() {
	'use strict';

	angular.module('issueTracker').controller('SMEController', SMEController);

	SMEController.$inject = [ '$state', 'HTTPService', '$scope', '$uibModal',
			'SelectHelper','ngDialog' ];
	function SMEController($state, HTTPService, $scope, $uibModal, SelectHelper,ngDialog) {
		console.log('SMEController');
		$scope.status1 = SelectHelper.getStatus();
		$scope.team1 = SelectHelper.getTeam();
		var trackerId = 1; //For SME Issue Tracker
		var issues = {};
		$scope.gridOptions = {
			enableFiltering : true,
			enableColumnResizing : true,
			enableGridMenu : true,
			rowHeight : 40,
			columnDefs : [
					{
						field : 'issueId',
						width : '5%',
						displayName : 'Id',
						type : 'number'
					},
					{
						field : 'issueLogDate',
						width : '8%',
						displayName : 'Date',
						type : 'date',
						cellTooltip : true
					},
					{
						field : 'upssTeam.teamName',
						width : '8%',
						displayName : 'Team',
						cellTooltip : true
					},
					{
						field : 'issueName',
						width : '15%',
						displayName : 'Issue',
						cellTooltip : true
					},
					{
						field : 'issueDesc',
						width : '20%',
						displayName : 'Issue Description',
						cellTooltip : true
					},
					{
						field : 'status',
						width : '9%',
						displayName : 'Status',
						cellTooltip : true
					},
					{
						field : 'pendingWith',
						width : '8%',
						displayName : 'Pending With',
						cellTooltip : true
					},
					{
						field : 'defectId',
						width : '7%',
						displayName : 'Defect Id',
						cellTooltip : true
					},
					{
						field : 'fixTimeLine',
						width : '7%',
						displayName : 'Fix TimeLine',
						cellTooltip : true
					},
					{
						field : 'closedDate',
						width : '5%',
						displayName : 'Closed Date',
						cellTooltip : true
					},
					{
						field : 'sysUpdateDate',
						width : '8%',
						displayName : 'Update Date',
						type : 'date',
						cellTooltip : true
					},
					{
						name : 'Options',
						width : '8%',
						cellClass : 'grid-align',
						enableFiltering : false,
						pinnedRight : true,
						cellTemplate : '<button class="btn btn-info" style="margin-right:10px" ng-click="grid.appScope.showInfo(row)"><i class="fa fa-info"></i></button>'
								+ '<button class="btn btn-danger" ng-click="grid.appScope.updateInfo(row)"><i class="fa fa-pencil-square-o"></i></button>'
					} ],
			data : $scope.issues
		};

		HTTPService.getAllData(trackerId).then(function(response) {
			$scope.issues = response.data;
			$scope.gridOptions.data = response.data;
		});

		$scope.updateInfo = function(row) {
			var modalInstance = $uibModal.open({
				controller : 'UpdateController',
				templateUrl : 'pages/updateInfo.html',
				size : 'lg',
				resolve : {
					selectedRow : function() {
						return row.entity;
					}
				}
			});
		};
		$scope.showInfo = function(row) {
			var modalInstance = $uibModal.open({
				controller : 'InfoController',
				templateUrl : 'ngTemplate/infoPopup.html',
				size : 'lg',
				resolve : {
					selectedRow : function() {
						return row.entity;
					}
				}
			});
		};

		$scope.getfreshData = function(team, status) {
			console.log("Length is :: " + $scope.gridOptions.data.length);
			if (!team || !status) {
  			  ngDialog.open({ 
 				  template: 'templateId', 
 				  scope:$scope,
 				  className: 'ngdialog-theme-default'
 			});
			} else {
				$scope.gridOptions.data.length = 0;
				HTTPService.getTeamIssues(team.value, status.value, trackerId)
						.then(function(response) {
							$scope.issues = response.data;
							$scope.gridOptions.data = response.data;
						});
			}
		};
		$scope.reFreshData = function() {
			$scope.gridOptions.data.length = 0;
			HTTPService.getAllData(trackerId).then(function(response) {
				$scope.issues = response.data;
				$scope.gridOptions.data = response.data;
			});
		};
		$scope.exportData = function(team, status) {
			if (!team || !status) {
	  			  ngDialog.open({ 
	 				  template: 'templateId', 
	 				  scope:$scope,
	 				  className: 'ngdialog-theme-default'
	 			});
				} else {
					HTTPService.exportData(team.value, status.value, trackerId)
							.then(function(response) {
								$scope.issues = response.data;
								$scope.gridOptions.data = response.data;
							});
				}
		};
	}

})();
