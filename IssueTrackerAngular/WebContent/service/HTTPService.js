(function () {
    'use strict';

    angular
        .module('issueTracker')
        .service('HTTPService', HTTPService);

    HTTPService.$inject = ['$http', '$q', '$cookies'];
    function HTTPService($http, $q, $cookies) {
        var service = {};
        service.getAllData = getAllData;                             //Get All Issues based on tracker Id
        service.getIssueDetails = getIssueDetails;                   //Get Any Details Regarding the Issue from IssueDetail table
        service.getSMEIssueSummary = getSMEIssueSummary;             //Get Issue Summary for Home Page i.e Dashboard
        service.getTeamIssues = getTeamIssues;                       //Filter issues based on team,status and trackerId
        service.getLatestIssueUpdates = getLatestIssueUpdates;       //Notification for Dashboard
        service.addIssue = addIssue;                                 //Add New Issue
        service.updateIssue = updateIssue;                           //Update Issue
        service.changePassword = changePassword;                     //Change Password
        service.exportData = exportData;                             //Export data to excel
        
        return service;
        function getAllData(trackerId){
			var deferred = $q.defer();
			$http.get('HomeServlet', {params : {action : 'getAllData', trackerId: trackerId}})
				.then(function(resp) {
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
					deferred.reject({ success: false, data: error});
			});
			return deferred.promise;
		}
        
        function getIssueDetails(issueId){
        	var deferred = $q.defer();
			$http.get('HomeServlet', {params : {action : 'viewDetails', issueId : issueId}})
				.then(function(resp) {
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
					deferred.reject({ success: false, data: error});
			});
			return deferred.promise;
        }
        
        function getSMEIssueSummary(teamId, trackerId){
        	var deferred = $q.defer();
        	$http.get('HomeServlet', {params : {action : 'getSMETrackerSummary', teamId: teamId, trackerId: trackerId}})
        	   .then(function(resp){
        		   deferred.resolve({success: true, data : resp.data});
        	   },
        	   function(error){
        		deffered.reject({success : false, data : error});
        	});
        	return deferred.promise;
        }
     
        function getTeamIssues(team, status, trackerId){
        	var deferred = $q.defer();
        	var action = 'getTeamIssue';
        	console.log("Tracker Id is :: "+trackerId);
			$http.get('FilterServlet', {params : {action : action, team:team,
				status : status, trackerId: trackerId}})
				.then(function(resp) {
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
					deferred.reject({ success: false, data: error});
			});
			return deferred.promise;
        }
        
        function getLatestIssueUpdates(){
        	var deferred = $q.defer();
        	var action = 'getLatestIssues';
			$http.get('FilterServlet', {params : {action : action }})
				.then(function(resp) {
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
					deferred.reject({ success: false, data: error});
			});
			return deferred.promise;
        }
        function addIssue(user, username){
        	var deferred = $q.defer();
        	console.log('Logdate :: '+user.logDate);
        	if(user.tracker.value == '2'){
        		$http({
    				method : 'POST',
    				url : 'HomeServlet',
    				data : $.param({action : 'addIssue',logDate : user.logDate, issueName : user.issueName, issueDesc : user.issueDescription,
    					status : user.status.value, reportedBy : user.reportedBy.value, pendingWith : user.pendingWith, remarks : user.remarks,
    					defectId : user.defectId, tracker : user.tracker.value, priority : user.priority.value, username: username,
    					frequency : user.frequency, ticket : user.ticket, deployed : user.deployed, occurrence : user.occurrence, efforts : user.efforts}),
    				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
    			})
    				.then(function(resp) {
    					console.log("Resolve Success");
    					deferred.resolve({success : true, data:resp.data});
    				},
    				function(error) {
    				console.log("Resolve Failure");
    					deferred.reject({ success: false, data: error});
    			});
        	} else{
        		$http({
    				method : 'POST',
    				url : 'HomeServlet',
    				data : $.param({action : 'addIssue',logDate : user.logDate, issueName : user.issueName, issueDesc : user.issueDescription,
    					status : user.status.value, reportedBy : user.reportedBy.value, pendingWith : user.pendingWith, remarks : user.remarks,
    					defectId : user.defectId, tracker : user.tracker.value, username: username}),
    				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
    			})
    				.then(function(resp) {
    					console.log("Resolve Success");
    					deferred.resolve({success : true, data:resp.data});
    				},
    				function(error) {
    				console.log("Resolve Failure");
    					deferred.reject({ success: false, data: error});
    			});
        	}
			  return deferred.promise;
        }
        function updateIssue(user, username, issue){
        	var deferred = $q.defer();
        	console.log('Logdate :: '+user.logDate);
        	$http({
				method : 'POST',
				url : 'HomeServlet',
				data : $.param({action : 'updateIssue',logDate : user.logDate,status : user.status.value, 
					pendingWith : user.pendingWith, remarks : user.remarks, issueId: issue.issueId, trackerId:issue.issueMaster.mId,
					defectId : user.defectId, fixTimeLine : user.fixTimeLine, closingDate: user.closingDate, username: username}),
				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			})
				.then(function(resp) {
					console.log("Resolve Success");
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
				console.log("Resolve Failure");
					deferred.reject({ success: false, data: error});
			});
        	return deferred.promise;
        }
        function changePassword(user, loginId){
        	var deferred = $q.defer();
        	$http({
				method : 'POST',
				url : 'LoginServlet',
				data : $.param({action : 'changePassword',oldPassword : user.oldPassword,newPassword : user.newPassword, 
					confirmPassword : user.confirmPassword, loginId : loginId}),
				headers : {'Content-Type': 'application/x-www-form-urlencoded'}
			})
				.then(function(resp) {
					console.log("Resolve Success");
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
				console.log("Resolve Failure");
					deferred.reject({ success: false, data: error});
			});
        	return deferred.promise;
        }
        function exportData(teamId, status, trackerId){
        	var deferred = $q.defer();
        	$http({
				method : 'POST',
				url : 'ExcelServlet',
				data : $.param({action : 'exportToExcel',teamId : teamId,status : status, trackerId: trackerId}),
				headers : {'Content-Type': 'Content-Disposition'},
        	    responseType:'application/vnd.ms-excel'
			})
				.then(function(resp) {
					console.log("Resolve Success");
					deferred.resolve({success : true, data:resp.data});
				},
				function(error) {
				console.log("Resolve Failure");
					deferred.reject({ success: false, data: error});
			});
        	return deferred.promise;
        }
        }
})();