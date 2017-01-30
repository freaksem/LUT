var app = angular.module('exchangeApp', ['ngRoute', 'ngResource']);

app.factory('CurrencyRate', function($resource) {
    return $resource('/api/rateRefresh');
});

app.factory('Operation', function($resource) {
    return $resource('operation');
});

app.config(function($routeProvider, $locationProvider, $httpProvider){
   $routeProvider
       .when('/',{
           templateUrl: '/tmpl/user.html',
           controller: 'userController'
       })
       .when('/login', {
           templateUrl: '/tmpl/login.html',
           controller: 'loginController'
       })
       .otherwise(
           {redirectTo: '/'}
       );
    $locationProvider.html5Mode(true);
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

var userControllerScope;
app.controller('userController', function($scope, $http, $window, CurrencyRate, Operation, $timeout){
    $http.get('/api/user/').then(function(response) {
        $scope.message = response.data;
        userControllerScope = response.data;
    });
    $scope.logout = function() {
        $http.post('logout', {}).finally(function(){
            $window.location.href="/";
        })
    };

    //$resourse module
    $scope.currenciesRates = '';
    $scope.currenciesRatesNames = CurrencyRate.get();
    $scope.reload = function () {
        $scope.currenciesRates = CurrencyRate.get();
        $timeout(function () {
            $scope.reload();
        }, 5000)
    };

    $scope.reload();

    $scope.operationSubmit=function(){
        var buyCurrency = angular.toJson($scope.operation.buy.$modelValue);
        var amountToBuy = angular.toJson($scope.operation.summ.$modelValue);
        var sellCurrency = angular.toJson($scope.operation.sell.$modelValue);
        //var token = angular.toJson($scope.operation..$modelValue);
        console.log(buyCurrency);
        console.log(amountToBuy);
        console.log(sellCurrency);
        //Operation.save()
    }
});

app.controller('loginController', function($scope, $http){
    $http.get('/login/').then(function(response) {
        $scope.message = response.data;
        $scope.userLogged = userControllerScope.loggedUserName;
    })
});