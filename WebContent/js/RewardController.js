var mbApp = angular

.module('mb', ['ui.bootstrap'])

.config(['$routeProvider', function($routeProvider) {
	  $routeProvider

.when('/dashboard', {templateUrl: '../html/partials/reward/dashboard.html', controller: productsController })
.when('/customers', {templateUrl: '../html/partials/reward/customers.html', controller: productsController })
.when('/transactions', {templateUrl: '../html/partials/reward/transactions.html', controller: editProductsController })
.when('/editCustomers', {templateUrl: '../html/partials/reward/editCustomers.html', controller: editProductsController })
	              .otherwise({redirectTo : '/dashboard.html'});

	  //$locationProvider.html5Mode(true);
}]);	

function RewardHomeController($scope) {
	 var leftStoreNavs = $scope.leftStoreNavs =  [];
		//$scope.mbHome-header = "Product";

	 leftStoreNavs.push({title:"<i style='font-size:20' class='glyphicon glyphicon-home'></i> &nbsp;&nbsp;Dashboard", link:"#dashboard", class:"active"});
	  //leftNavs.push({title:"Insights", link:"#insights", class:"inactive"});
	 leftStoreNavs.push({title:"<i style='font-size:20' class='glyphicon glyphicon-user'></i>&nbsp;&nbsp; Customers", link:"#customers", class:"inactive"});
	 leftStoreNavs.push({title:"<i style='font-size:20' class='glyphicon glyphicon-edit'></i>&nbsp;&nbsp; Transactions", link:"#transactions", class:"inactive"});
	 leftStoreNavs.push({title:"<i style='font-size:20' class='glyphicon glyphicon-search'></i>&nbsp;&nbsp; Search", link:"#search", class:"inactive"});

	  $scope.leftStoreNavClicked = function($event, selectedIndex) {
		     for(var i = 0 ; i < leftStoreNavs.length; i++) {
		      if(i === selectedIndex) {
		          $scope.leftStoreNavs[i].class="active";
		      }else{
		          $scope.leftStoreNavs[i].class="inactve";
		      }
		      
		    }
		  };	 
}


function editProductsController($scope) {
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

}

function productsController($scope){
	
}

