//angular.module('bizSpark', ['ui.bootstrap']);

var bizSparkApp = angular.module('bizSpark', ['ui.bootstrap']).config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/sendCoupon', {templateUrl: 'partials/sendCoupon.html', controller: sendCouponController }).
	              when('/redeemCoupon', {templateUrl: 'partials/redeemCoupon.html', controller: CouponController }).
	              when('/insights', {templateUrl: 'partials/insights.html', controller: CoreController }).
	              when('/customers', {templateUrl: 'partials/customers.html', controller: CustomerController}).
	              when('/howitworks', {templateUrl: 'partials/howItWorks.html', controller: CoreController}).
	              otherwise({redirectTo : '/sendCoupon'});

	  //$locationProvider.html5Mode(true);
}]);	

function MainController($scope, $http) {

  $scope.projectName = "<span style='color:#a1ce69'>Refer</span><span style='color:#eef0eb'>Spark</span>";
  
  $scope.menu_more = "More<span class='caret'></span>";
  $scope.labelEmail = "Email Address";
  $scope.labelpassword = "Password";
  $scope.labelReEnterPassword = "Re-Enter Password";
  $scope.labelPhone = "Phone Number (optional)";
  
  var slides = $scope.slides = [];
  $scope.myInterval = 5000;
  slides.push({image:"", title:"Create Referral Coupons", content: "Existing loyal customers will REFER your business to their friends and colleagues and drive new business.", active: true}); 
  slides.push({image:"", title:"Reward your Loyal Customers ", content: "REWARD your customers for each successful referral they get to your store with discounts and offers !", active: false}); 
  slides.push({image:"", title:"Grow your Business", content: "Our recommendation engine will track all your referral coupons and provide insightful reports on how your business is GROWing.", active: false}); 

  var menuOptions = $scope.menuOptions = [];
  menuOptions.push({state:"disabled", name:"Sign In", url : "#signin"});
  menuOptions.push({state:"disabled", name:"Help", url : "#help"});
  menuOptions.push({state:"disabled", name:"Pricing", url : "#pricing"});
  menuOptions.push({state:"divider", name:""}); 
  menuOptions.push({state:"disabled", name:"How To Videos", url : "#video"});
  menuOptions.push({state:"disabled", name:"Live Community", url : "#community"});

  var menus = $scope.menus = [];
  menus.push({name:"Blog", url : "#blogs"});
  menus.push({name:"Contact Us", url : "mailto:contact@referspark.com?subject=Help Needed"});
  //menus.push({name:"Sign In", url : "#contact"});
  

  var features = $scope.features = [];
  features.push({image: "assets/images/browser-icon-chrome.png", title:"Create Referral Coupons", subtitle: "most effective way to drive new customers.", description: "We help you create and manage referral based coupons to issue to your customers. Choose from a variety of templates and let your customers use all social channels for effective referal marketing."});
  features.push({image: "assets/images/browser-icon-firefox.png",  title:"Reward Loyal customers", subtitle: "will help you retain your existing customers.",  description: "We help you in efficiently engaging and rewarding your regular customers. Redeem coupons at your finger tips and have many happy customers."});
  features.push({image: "assets/images/browser-icon-safari.png", title:"Grow your Business", subtitle: "by efficiently tracking and managing your campaigns.",  description: "Our smart algorigthms will provide you insightful data and ensure you are a step ahead of your neighbour. The reports are extensive and can be drilled down to get valuable information."});

    $scope.signupEmailFocus = false;
    $scope.initiateSignUp = function() {
      $scope.signupEmailFocus = true;

    };
    
    $scope.user = {};
    $scope.registeruser = function() {
    	//alert($scope.user.email);
    	//alert($scope.user.number);
    	
    	$http({
    		method : 'GET',
    		url : '../api/addPotentialCustomer.json' + '?' + 'email=' + $scope.user.email + '&mobile=' + $scope.user.number + '&businessname=' + $scope.user.businessName 
    	}).success(function(data, status, headers){
    		$scope.userAddSuccess = true;
    	}).error(function(data, status, header){
    		$scope.userAddError = true;
    		//alert('Error in creating user');
    	});
    	
    };
    
    $scope.userAddSuccess = false;
    $scope.userAddError = false;
};  



function CoreController($scope) {

  var cmenus = $scope.cmenus = [];
  cmenus.push({name:"Biz Spark", class:"brand" , link : "#"});
  cmenus.push({name:"My Account" ,class:"nav pull-right", link:"#"});
  cmenus.push({name:"Signout", class:"nav pull-right", link:"#"});
  
  var leftNavs = $scope.leftNavs =  [];
  leftNavs.push({title:"<i class='icon-envelope'></i>&nbsp;Send a coupon", link:"#sendCoupon", class:"active"});
  //leftNavs.push({title:"Insights", link:"#insights", class:"inactive"});
  leftNavs.push({title:"<i class='icon-share'></i>&nbsp;Redeem a coupon", link:"#redeemCoupon", class:"inactive"});
  leftNavs.push({title:"<i class='icon-user'></i>&nbsp;Manage customers", link:"#customers", class:"inactive"});
  leftNavs.push({title:"<i class='icon-facetime-video'></i>&nbsp;See how it works", link:"#howitworks", class:"inactive"});
  
  
  $scope.leftNavClicked = function($event, selectedIndex) {
     for(var i = 0 ; i < leftNavs.length; i++) {
      if(i === selectedIndex) 
          $scope.leftNavs[i].class="active";
      else
          $scope.leftNavs[i].class="inactve";
      
    }
  };



};


function CustomerController($scope) {

  var customers = $scope.customers = [];
  customers.push({name:"Manish", email:"Manish_kumar@intuit.com", phone:"9886618989", loyaltyPoints:"20"});
 customers.push({name:"Ajay", email:"Manish_kumar@intuit.com", phone:"123455353", loyaltyPoints:"20"});
 customers.push({name:"Manas", email:"Manish_kumar@intuit.com", phone:"1234567890", loyaltyPoints:"75"});
 customers.push({name:"Fazil", email:"Manish_kumar@intuit.com", phone:"2222222222", loyaltyPoints:"200"});
 customers.push({name:"Siva", email:"Manish_kumar@intuit.com", phone:"7654324332", loyaltyPoints:"10"});
 customers.push({name:"Ajit", email:"Manish_kumar@intuit.com", phone:"454312313", loyaltyPoints:"0"});
  customers.push({name:"Ajay", email:"Manish_kumar@intuit.com", phone:"123455353", loyaltyPoints:"20"});
 customers.push({name:"Manas", email:"Manish_kumar@intuit.com", phone:"1234567890", loyaltyPoints:"75"});
 customers.push({name:"Fazil", email:"Manish_kumar@intuit.com", phone:"2222222222", loyaltyPoints:"200"});
 customers.push({name:"Siva", email:"Manish_kumar@intuit.com", phone:"7654324332", loyaltyPoints:"10"});
 customers.push({name:"Ajit", email:"Manish_kumar@intuit.com", phone:"454312313", loyaltyPoints:"0"});
 
 $scope.myData = [{name: "Moroni", age: 50},
                 {name: "Tiancum", age: 43},
                 {name: "Jacob", age: 27},
                 {name: "Nephi", age: 29},
                 {name: "Enos", age: 34}];

  $scope.gridOptions = { data: 'myData' };
  

  $scope.noOfPages = 7;
  $scope.currentPage = 4;
  $scope.maxSize = 5;
  
  $scope.setPage = function (pageNo) {
    $scope.currentPage = pageNo;
  };

  $scope.bigNoOfPages = 18;
  $scope.bigCurrentPage = 1;

  function selectCustomer($event) {
    alert('selected');
  }

};

function sendCouponController($scope) {
	$scope.selected = undefined;
	$scope.customers = ['Manish', 'Kumar', 'Ajay', 'Francis', 'Manas', 'Panda', 'Sivaraman'];
	var todo = $scope.todo = {};
	todo.title='sdf';
	var blueTemplate = $scope.blueTemplate = {};
	blueTemplate.line1 = "Thank you for shopping with us!!";
	blueTemplate.line2 = "Now, earn points by referring our business to your friends. Each succesful referral will earn you reward points which you can redeem in your next purchase";
	blueTemplate.line3 = "Just forward this email or the referral code to your friends" +
										"and colleagues who might be interested.";
	blueTemplate.line4 = " Each of them gets FLAT 30% OFF";

};


function CouponController($scope) {
  $scope.isCollapsed = true;

};



//On esc event
bizSparkApp.directive('onEsc', function() {
  return function(scope, elm, attr) {
    elm.bind('keydown', function(e) {
      if (e.keyCode === 27) {
        scope.$apply(attr.onEsc);
      }
    });
  };
});

// On enter event
bizSparkApp.directive('onEnter', function() {
  return function(scope, elm, attr) {
    elm.bind('keypress', function(e) {
      if (e.keyCode === 13) {
        scope.$apply(attr.onEnter);
      }
    });
  };
});

// Inline edit directive
bizSparkApp.directive('inlineEdit', function($timeout) {
  return {
    scope: {
      model: '=inlineEdit',
      handleSave: '&onSave',
      handleCancel: '&onCancel'
    },
    link: function(scope, elm, attr) {
      var previousValue;
      
      scope.edit = function() {
        scope.editMode = true;
        previousValue = scope.model;
        
        $timeout(function() {
          elm.find('input')[0].focus();
        }, 0, false);
      };
      scope.save = function() {
        scope.editMode = false;
        scope.handleSave({value: scope.model});
      };
      scope.cancel = function() {
        scope.editMode = false;
        scope.model = previousValue;
        scope.handleCancel({value: scope.model});
      };
    },
    templateUrl: 'partials/inline-edit.html'
  };
});






