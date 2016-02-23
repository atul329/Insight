(function() {

	'use strict';

	angular.module('issueTracker').factory('SelectHelper', [ SelectHelper ]);
	function SelectHelper() {
		var service = {};

		service.getTeam = getTeam;
		service.getTracker = getTracker;
		service.getPartialStatus = getPartialStatus;
		service.getStatus = getStatus;
        service.getPriority = getPriority;
        service.getUpdateStatus = getUpdateStatus;
        service.getTeamforAddIssue = getTeamforAddIssue;
		
		return service;

		function getTeam() {
			var team = [ {
				"name" : "SME",
				"value" : "1"
			}, {
				"name" : "VAS",
				"value" : "2"
			}, {
				"name" : "Mediation",
				"value" : "3"
			}, {
				"name" : "Provisioning",
				"value" : "4"
			}, {
				"name" : "Configuration",
				"value" : "5"
			}, {
				"name" : "All",
				"value" : "1"
			} ];

			return team;
		}
		function getTeamforAddIssue(){
			var team = [ {
				"name" : "SME",
				"value" : "1"
			}, {
				"name" : "VAS",
				"value" : "2"
			}, {
				"name" : "Mediation",
				"value" : "3"
			}, {
				"name" : "Provisioning",
				"value" : "4"
			}, {
				"name" : "Configuration",
				"value" : "5"
			}, {
				"name" : "Abhishek Mehta",
				"value" : "6"
			} ];

			return team;
		}
		function getTracker() {
			var tracker = [ {
				"name" : "SME Issue Tracker",
				"value" : "1"
			}, {
				"name" : "WorkAround Tracker",
				"value" : "2"
			}, {
				"name" : "Health Check Tracker",
				"value" : "3"
			}, {
				"name" : "Internal Tracker",
				"value" : "4"
			} ];
			return tracker;
		}

		function getPartialStatus() {
			var status = [ {
				"name" : "Under Monitoring",
				"value" : "UNDER MONITORING"
			}, {
				"name" : "Open",
				"value" : "OPEN"
			} ];
			return status;
		}

		function getStatus() {
			var status = [ {
				"name" : "Open",
				"value" : "OPEN"
			}, {
				"name" : "Under Monitoring",
				"value" : "UNDER MONITORING"
			}, {
				"name" : "Closed",
				"value" : "CLOSED"
			}, {
				"name" : "ReOpen",
				"value" : "REOPEN"
			}, {
				"name" : "All",
				"value" : "ALL"
			} ];
			return status;
		}
		function getPriority() {
			var priority = [ {
				"name" : "P1",
				"value" : "P1"
			}, {
				"name" : "P2",
				"value" : "P2"
			}, {
				"name" : "P3",
				"value" : "P3"
			} ];
			return priority;
		}
		function getUpdateStatus(){
			var status = [ {
				"name" : "Open",
				"value" : "OPEN"
			}, {
				"name" : "Under Monitoring",
				"value" : "UNDER MONITORING"
			}, {
				"name" : "Closed",
				"value" : "CLOSED"
			}];
			return status;
		}
	}
})();