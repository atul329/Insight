(function() {
	'use strict';
	angular.module('issueTracker', ['ui.bootstrap', 'ui.router', 'ngCookies', 'ngMessages', 'oc.lazyLoad','ui.grid', 'ui.grid.resizeColumns', 'ui.grid.autoResize', 'ui.grid.pinning', 'ui.grid.selection','angular-loading-bar', 'ngAnimate','ngDialog'])
			.config(config)
			.run(run);
	config.$inject = [ '$stateProvider', '$urlRouterProvider', '$compileProvider'];
	function config($stateProvider, $urlRouterProvider,$compileProvider) {
		$compileProvider.debugInfoEnabled(false);
		$urlRouterProvider.otherwise('login');
		$stateProvider.state('login', {
			url : '/login',
			controller : 'LoginController',
			templateUrl : 'pages/login.html',	
			controllerAs : 'vm'
		})
		.state('tracker.dashboard',{
			url : '/dasboard',
			templateUrl : 'directive/dashboard/dashboard.html',
			resolve : {
				loadMyDirectives : function($ocLazyLoad) {
					return $ocLazyLoad.load(
    		                {
    		                    name:'issueTracker',
    		                    files:[
    		                    'controller/homeController.js',
    		                    'directive/dashboard/stats/stats.js',
    		                    'directive/dashboard/notification/notification.js'
    		                    ]
    		                });
    		}
    	},
    	controller : 'HomeController'
		})
		.state('tracker.addIssue',{
			url : '/addIssue',
			templateUrl : 'pages/addIssue.html',
			resolve : {
				loadMyDirectives : function($ocLazyLoad) {
					return $ocLazyLoad.load(
    		                {
    		                    name:'issueTracker',
    		                    files:[
                                  'controller/addIssueCtrl.js',
                                  'css/addIssue.css'
    		                    ]
    		                });
    		}
    	},
    	controller : 'AddIssueCtrl'
		})
		.state('tracker.sme',
				{
					url : '/sme',
					templateUrl : 'pages/home.html',
					resolve : {
						loadMyDirectives : function($ocLazyLoad) {
							return $ocLazyLoad.load(
    	    		                {
    	    		                    name:'issueTracker',
    	    		                    files:[
    	    		                    'controller/smeController.js',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.min.css',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.eot',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.svg',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.ttf',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.woff',
    	    		                    'css/updateModal.css',
    	    		                    'controller/updateController.js'
    	    		                    ]
    	    		                });
    	    		}
    	    	},
		        controller : 'SMEController'
    	    })
    	    .state('tracker.healthCheck',
				{
					url : '/healthCheck',
					templateUrl : 'pages/home.html',
					resolve : {
						loadMyDirectives : function($ocLazyLoad) {
							return $ocLazyLoad.load(
    	    		                {
    	    		                    name:'issueTracker',
    	    		                    files:[
    	    		                    'controller/healthCheckController.js',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.min.css',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.eot',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.svg',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.ttf',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.woff',
    	    		                    'css/updateModal.css',
    	    		                    'controller/updateController.js'
    	    		                    ]
    	    		                });
    	    		}
    	    	},
		        controller : 'HealthCheckCtrl'
    	    })
    	    .state('tracker.workAround',
				{
					url : '/workAround',
					templateUrl : 'pages/home.html',
					resolve : {
						loadMyDirectives : function($ocLazyLoad) {
							return $ocLazyLoad.load(
    	    		                {
    	    		                    name:'issueTracker',
    	    		                    files:[
    	    		                    'controller/workAroundController.js',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.min.css',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.eot',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.svg',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.ttf',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.woff',
    	    		                    'css/updateModal.css',
    	    		                    'controller/updateController.js'
    	    		                    ]
    	    		                });
    	    		}
    	    	},
		        controller : 'WorkAroundCtrl'
    	    })
    	    .state('tracker.internal',
				{
					url : '/internal',
					templateUrl : 'pages/home.html',
					resolve : {
						loadMyDirectives : function($ocLazyLoad) {
							return $ocLazyLoad.load(
    	    		                {
    	    		                    name:'issueTracker',
    	    		                    files:[
    	    		                    'controller/internalController.js',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.min.css',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.eot',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.svg',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.ttf',
    	    		                    'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/3.0.7/ui-grid.woff',
    	    		                    'css/updateModal.css',
    	    		                    'controller/updateController.js'
    	    		                    ]
    	    		                });
    	    		}
    	    	},
		        controller : 'InternalController'
    	    })
		.state('tracker',
				{
					url : '/tracker',
					templateUrl : 'pages/main.html',
					resolve : {
						loadMyDirectives : function($ocLazyLoad) {
							return $ocLazyLoad.load({
								name : 'issueTracker',
								files : [ 
								        'directive/header/header.js',
										'directive/header/header.css',
										'controller/passwordCtrl.js',
		                                'css/passwordModal.css'
								        ]
							});
						}
					},
					controller : 'mainController'
				})
	    }
	    angular.module('issueTracker')
	    .controller('mainController', function($cookies, $rootScope, $scope, $state, HTTPService){
	    	$scope.empName = $cookies.get('empName');
	    });
	    angular.module('issueTracker').controller('InfoController', 
	    	    ['$scope', '$uibModalInstance', 'selectedRow', 'HTTPService',
	    	    function ($scope, $uibModalInstance, selectedRow, HTTPService) {
	    	    	HTTPService.getIssueDetails(selectedRow.issueId).then(function(response){
	            		$scope.issueDetailList = response.data;
	            	});
	    	        $scope.selectedRow = selectedRow;
	    	        console.log('InfoController :: '+selectedRow.issueId);

	    	       $scope.ok = function () {
	    	            $scope.selectedRow = null;
	    	            $uibModalInstance.close();
	    	        };
	    	    }
	    	]);
	    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
	    function run($rootScope, $location, $cookies, $http) {
	        // keep user logged in after page refresh
	    	$rootScope.globals = $cookies.get('globals') || {};
	        if ($rootScope.globals.currentUser) {
	            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
	        }
	        $rootScope.$on('$locationChangeStart', function (event, next, current) {
	            // redirect to login page if not logged in and trying to access a restricted page
	            var restrictedPage = $.inArray($location.path(), ['/login']) === -1;
	            var loggedIn = $cookies.get('currentUser');           
	            if (restrictedPage && !loggedIn) {
	                $location.path('/login');
	            }
	        });
	    }
})();