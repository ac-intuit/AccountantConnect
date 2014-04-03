var mbApp = angular

.module('AccountantProfileSection', ['ui.bootstrap'])

.config(['$routeProvider', function($routeProvider) {
	  $routeProvider

.when('/basic_info', {templateUrl: '../ac/partials/editprofile_basicinfo.html', controller: PartialProfileViewController  })
.when('/photo', {templateUrl: '../ac/partials/editprofile_photo.html', controller: PhotoController })
.when('/description', {templateUrl: '../ac/partials/editprofile_description.html', controller: PartialProfileViewController  })
.when('/social', {templateUrl: '../ac/partials/editprofile_social.html', controller: PartialProfileViewController  })
.when('/exp', {templateUrl: '../ac/partials/editprofile_exp.html', controller:AccountantController})
.when('/education', {templateUrl: '../ac/partials/editprofile_education.html'})
.when('/address', {templateUrl: '../ac/partials/editprofile_address.html'})
	              .otherwise({redirectTo : '/basic_info'});

	  //$locationProvider.html5Mode(true);
}]);	

/**
 * Main controller for the accountant profile screen
 * @param $scope
 * @param $http
 * @returns
 */
function AccountantController($scope, $http, $location, $rootScope) {
	$scope.accountant = [];
	$scope.expertises = [{id: 'expertise1',value: ''}, {id: 'expertise2',value: ''}, {id: 'expertise3',value: ''}];
	
    $scope.getAccountant = function() {
        $http({method: 'GET', url: '../api/accountant/profile/1120'}).
            success(function(data, status, headers, config) {
            	if(data.code == 1){
	                $scope.accountant = data.data;
	                //build the expertise fields from "areasOfExpertise"
	                var expertiseString = $scope.accountant.areasOfExpertise;
	                var expertiseArr = expertiseString.split("|");
	                //If no expertise is entered, just show the 3 text boxes
	                if(expertiseArr.length != 0){
	                	$scope.expertises = [];
	                	for(var i = 0; i < expertiseArr.length; i++){
	                		var id = i+1;
	                		var val = expertiseArr[i];
	              		    $scope.expertises.push({'id':'expertise' + id,'value': val});
	                	}
	                }
	            }else{
	            	$scope.errorMessageFromServer = data.description;
	            }
            }).
            error(function(data, status, headers, config) {
                $scope.apps = data || "Request failed";
                $scope.status = status;
                $scope.view = './Partials/list.html';
            });
  };
                
  $scope.getAccountant();
  $scope.successMessageDivVisibility = "none";       
  $scope.errorMessageDivVisibility = "none";       
	  
	  //Save and Next
		$scope.saveFormDataAndNextView = function saveFormDataAndNextView(nextView) {
			$scope.successMessageDivVisibility = "none"; 
        	$scope.errorMessageDivVisibility = "none";  
        	//Add all the expertises and make a string.
        	var expertiseString = "";
        	var expertiseCount = 0;
        	for(var i = 1; i < $scope.expertises.length+1; i++){
        		var expertiseVal = $('#expertise'+i).val();
        		if(expertiseVal != ""){
        			if(expertiseCount != 0){
        				expertiseString += '|';
        			}
        			expertiseString += expertiseVal;
        			expertiseCount++;
        		}
        	}
        	//Add expertise to the scope
        	$scope.accountant.areasOfExpertise = expertiseString;
	        $http({
	        	method: 'POST', 
	        	url: '../api/accountant/saveProfile', 
	        	data: $.param($scope.accountant),
	        	headers : { 'Content-Type': 'application/x-www-form-urlencoded'}}).
	            success(function(data, status, headers, config) {
	            	if(data.code == 1){   		                
		                if(nextView == null){
		                	$scope.successMessageDivVisibility = "none";       
		                	$scope.successMessageDivVisibility = "block";       
		                	$scope.successMessageDivSuccessMessage = data.description;
		                }else{		                	   
			                $scope.successMessageDivSuccessMessage = data.description;
			                $location.path(nextView);
		                }
		            }else{
		            	$scope.successMessageDivVisibility = "none"; 
		            	$scope.errorMessageDivVisibility = "block";       
		                $scope.errorMessageDivSuccessMessage = data.description;
		            }
	            }).
	            error(function(data, status, headers, config) {
	            	$scope.successMessageDivVisibility = "none"; 
	            	$scope.errorMessageDivVisibility = "block";       
	                $scope.errorMessageDivSuccessMessage = data.description;
	            });
	  };
	  
	   //Save form data
		$scope.saveFormData = function saveFormData() {
			$scope.saveFormDataAndNextView(null);
		};
	  
	  $scope.addNewExpertise = function() {
		  var newItemNo = $scope.expertises.length+1;
		  $scope.expertises.push({'id':'expertise' + newItemNo});
	  };
}
AccountantController.$inject = ['$scope', '$http', '$location', '$rootScope'];

/**
 * PartialProfileViewController 
 * @param $scope
 * @param $http
 * @param $templateCache
 */
function PartialProfileViewController($scope, $http, $templateCache){
	//alert("hello");	
}

function PhotoController($scope, $http, $templateCache){
	$scope.actualImgDisplay = "hidden";
	$scope.dummyImgDisplay = "";
	
	/**
	 * To handle image upload
	 */
	$(function() {

		$('#file-input-button').click(function() {
			// Simulate a click on the file input button
			// to show the file browser dialog
			$('#fileupload').click();
			$scope.photoImgSrc = "img";
		});

		
		// Initialize the jQuery File Upload plugin
		$('#upload1').fileupload({

			// This function is called when a file is added to the queue;
			// either via the browse button, or via drag/drop:
			add : function(e, data) {
				
				// Automatically upload the file once it is added to the queue
				var jqXHR = data.submit();
			},
			progress : function(e, data) {

			},
			fail : function(e, data) {
			},
			success : function(e, data) {
				$("#photoImgSrc").attr("src", e[0].url);
			},
			
			
		});
		// Helper function that formats the file sizes
		function formatFileSize(bytes) {
			if (typeof bytes !== 'number') {
				return '';
			}
			if (bytes >= 1000000000) {
				return (bytes / 1000000000).toFixed(2) + ' GB';
			}
			if (bytes >= 1000000) {
				return (bytes / 1000000).toFixed(2) + ' MB';
			}
			return (bytes / 1000).toFixed(2) + ' KB';
		}
	});
}


function EditProfileSideBarController($scope) {

	//Create left nav of the edit profile
	var editProfileLeftNavs_intro = $scope.editProfileLeftNavs_intro = [];
	var editProfileLeftNavs_prof = $scope.editProfileLeftNavs_prof = [];

	editProfileLeftNavs_intro.push({
		class : "active",
		link : "basic_info",
		glyphicon : "glyphicon-user",
		LinkText : "Basic Info",
		IsItemFilled : "glyphicon-plus"
	});
	editProfileLeftNavs_intro.push({
		class : "inactive",
		link : "photo",
		glyphicon : "glyphicon-camera",
		LinkText : "Photo",
		IsItemFilled : "glyphicon-plus"
	});
	editProfileLeftNavs_intro.push({
		class : "inactive",
		link : "description",
		glyphicon : "glyphicon-file",
		LinkText : "Description",
		IsItemFilled : "glyphicon-plus"
	});
	editProfileLeftNavs_intro.push({
		class : "inactive",
		link : "social",
		glyphicon : "glyphicon-thumbs-up",
		LinkText : "Social Profile",
		IsItemFilled : ""
	});

	editProfileLeftNavs_prof.push({
		class : "inactive",
		link : "exp",
		glyphicon : "glyphicon-briefcase",
		LinkText : "Experience",
		IsItemFilled : "glyphicon-plus"
	});
	editProfileLeftNavs_prof.push({
		class : "inactive",
		link : "education",
		glyphicon : "glyphicon-book",
		LinkText : "Education",
		IsItemFilled : ""
	});
	editProfileLeftNavs_prof.push({
		class : "inactive",
		link : "address",
		glyphicon : "glyphicon-map-marker",
		LinkText : "Contact",
		IsItemFilled : "glyphicon-plus"
	});
	
	
	//Watch the location change. If it happens, update the sidebar as well
	$scope.$on('$locationChangeSuccess', function (event, newLoc, oldLoc){
		//Make everything inactive
		for ( var i = 0; i < editProfileLeftNavs_intro.length; i++) {
			$scope.editProfileLeftNavs_intro[i].class = "inactive";			
		}
		for ( var i = 0; i < editProfileLeftNavs_prof.length; i++) {
			$scope.editProfileLeftNavs_prof[i].class = "inactve";
		}
		//search which nav corresponds to the URL
		for ( var i = 0; i < editProfileLeftNavs_intro.length; i++) {
			if(newLoc.indexOf($scope.editProfileLeftNavs_intro[i].link) != -1){
			   $scope.editProfileLeftNavs_intro[i].class = "active";
			   return;
			}
		}
		for ( var i = 0; i < editProfileLeftNavs_prof.length; i++) {
			if(newLoc.indexOf($scope.editProfileLeftNavs_prof[i].link) != -1){
				$scope.editProfileLeftNavs_prof[i].class = "active";
			}
		}
	});
	
	
	
}
