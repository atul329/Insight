(function () {
    'use strict';

    angular
        .module('issueTracker')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$state', 'AuthenticationService', 'HTTPService', '$scope', '$cookies'];
    function HomeController($state, AuthenticationService, HTTPService, $scope, $cookies) {
    	var cookiesDetails = $cookies.get('currentUser');
        var userDetail = cookiesDetails?JSON.parse(cookiesDetails):[];
    	HTTPService.getSMEIssueSummary(userDetail.teamId, userDetail.trackerId).then(function(response){
    		$scope.summary = response.data;
    	});
    }
})();