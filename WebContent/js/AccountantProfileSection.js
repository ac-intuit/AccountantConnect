var mbApp = angular

.module('AccountantProfileSection', ['ui.bootstrap'])

.config(['$routeProvider', function($routeProvider) {
	  $routeProvider

.when('/basic_info', {templateUrl: '../ac/partials/editprofile_basicinfo.html', controller: EditProfileSideBarController })
.when('/experience', {templateUrl: '../ac/partials/editprofile_photo.html', controller: EditProfileSideBarController })
	              .otherwise({redirectTo : '/basic_info'});

	  //$locationProvider.html5Mode(true);
}]);	


function EditProfileSideBarController($scope){

	//Create left nav of the edit profile
	var editProfileLeftNavs_intro = $scope.editProfileLeftNavs_intro = [];
	var editProfileLeftNavs_prof = $scope.editProfileLeftNavs_prof = [];

	editProfileLeftNavs_intro.push({class: "active",link: "#basic_info",glyphicon: "glyphicon-user", LinkText: "Basic Info", IsItemFilled: "glyphicon-plus"});
	editProfileLeftNavs_intro.push({class: "inactive",glyphicon: "glyphicon-camera", LinkText: "Photo", IsItemFilled: "glyphicon-plus"});
	editProfileLeftNavs_intro.push({class: "inactive",glyphicon: "glyphicon-file", LinkText: "Brief Description", IsItemFilled: "glyphicon-plus"});
	editProfileLeftNavs_intro.push({class: "inactive",glyphicon: "glyphicon-thumbs-up", LinkText: "Social Profile", IsItemFilled: "glyphicon-plus"});
	
	editProfileLeftNavs_prof.push({class: "inactive",glyphicon: "glyphicon-briefcase", LinkText: "Experience", IsItemFilled: "glyphicon-plus"});
	editProfileLeftNavs_prof.push({class: "inactive",glyphicon: "glyphicon-book", LinkText: "Education", IsItemFilled: "glyphicon-plus"});
	editProfileLeftNavs_prof.push({class: "inactive",glyphicon: "glyphicon-map-marker", LinkText: "Contact", IsItemFilled: "glyphicon-plus"});

	/*
	*  Code to change the selected item in the left navbar
	*/
	 $scope.editProfileLeftNavs_introClicked = function($event, selectedIndex) {

	 		//Except the selected index make everything inactive
		     for(var i = 0 ; i < editProfileLeftNavs_intro.length; i++) {
		      if(i === selectedIndex) {
		          $scope.editProfileLeftNavs_intro[i].class="active";
		      }else{
		          $scope.editProfileLeftNavs_intro[i].class="inactve";
		      }
		      
		    }
		    //Make everything of the "editProfileLeftNavs_prof" inactive
		    for(var i = 0 ; i < editProfileLeftNavs_prof.length; i++) {		      
     	          $scope.editProfileLeftNavs_prof[i].class="inactve";		      
		    }
		  };	

	 $scope.editProfileLeftNavs_profClicked = function($event, selectedIndex) {
			     for(var i = 0 ; i < editProfileLeftNavs_prof.length; i++) {
			      if(i === selectedIndex) {
			          $scope.editProfileLeftNavs_prof[i].class="active";
			      }else{
			          $scope.editProfileLeftNavs_prof[i].class="inactve";
			      }
			      
			    }
				//Make everything of the "editProfileLeftNavs_prof" inactive
			    for(var i = 0 ; i < editProfileLeftNavs_intro.length; i++) {		      
	     	          $scope.editProfileLeftNavs_intro[i].class="inactve";		      
			    }
			  };
}
