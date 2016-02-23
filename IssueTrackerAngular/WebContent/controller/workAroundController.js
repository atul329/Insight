(function () {
    'use strict';

    angular
        .module('issueTracker')
        .controller('WorkAroundCtrl', WorkAroundCtrl);

    WorkAroundCtrl.$inject = ['$state', 'HTTPService', '$scope', '$uibModal'];
    function WorkAroundCtrl($state, HTTPService, $scope, $uibModal) {
        console.log('WorkAroundCtrl');
        var issues = {};
        $scope.gridOptions = {
        	    enableFiltering: true,
        	    enableColumnResizing: true,
        	    rowHeight : 40,
        	    columnDefs: [
        	        { field: 'issueId', width: '5%', displayName : 'Id', type :'number'},
        	        { field: 'issueLogDate', width: '8%', displayName:'Date', type: 'date'},
        	        { field: 'upssTeam.teamName', width: '8%', displayName: 'Team'},
        	        { field: 'issueName', width: '15%', displayName: 'Issue',  cellTooltip: true},
        	        { field: 'issueDesc', width: '20%', displayName: 'Issue Description', cellTooltip: true},
        	        { field: 'status', width: '9%', displayName: 'Status'},
        	        { field: 'pendingWith', width: '8%', displayName: 'Pending With'},
        	        { field: 'defectId', width: '7%', displayName: 'Defect Id'},
        	        { field: 'fixTimeLine', width: '7%', displayName: 'Fix TimeLine'},
        	        { field: 'closedDate', width: '5%', displayName: 'Closed Date'},
        	        { field: 'sysUpdateDate', width: '8%', displayName: 'Update Date', type :'date'},
        	        { name : 'Options', width: '8%',cellClass: 'grid-align',
        	          cellTemplate:'<button class="btn btn-info" ng-click="grid.appScope.showInfo(row)"><i class="fa fa-info-circle"></i></button>'+
        	        	 '<button class="btn btn-warning" ng-click="grid.appScope.moreInfo(row.entity)"><i class="fa fa-info"></i></button>'}
        	    ],
        	    data: $scope.issues
        	};
        $scope.showMe = true;
        $scope.moreInfo = function(row) {
        	console.log("Inside MoreInfo .. :)");
        	$scope.showMe = false;
        	$scope.issueDescription = {
        			issueLoggedDate : row.issueLogDate,
        			issueName : row.issueName,
        			issueDesc : row.issueDesc
        	};
        	HTTPService.getIssueDetails(row.issueId).then(function(response){
        		$scope.issueDetailList = response.data;
        	});
        };
        $scope.showInfo = function(row) {
           var modalInstance = $uibModal.open({
                controller: 'InfoController',
                templateUrl: 'ngTemplate/infoPopup.html',
                resolve: {
                  selectedRow: function () {                    
                      return row.entity;
                  }
                }
           });
        }

        HTTPService.getAllData().then(function(response){
        	$scope.issues = response.data;
        	$scope.gridOptions.data = response.data;
        });
    }

})();
