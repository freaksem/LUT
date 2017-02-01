var app = angular.module('exchangeApp', ['ngRoute', 'ngResource']);

app.factory('CurrencyRate', function($resource) {
    return $resource('/api/rateRefresh');
});

app.factory('Operation', function($resource) {
    return $resource('/api/operation');
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
        var currencyToBuy = $scope.operation.buy.$modelValue;
        var currencyToSell = $scope.operation.sell.$modelValue;
        var summToBuy = ($scope.operation.summ.$modelValue).toString();

        var operationData = {
            currencyToBuyParam: currencyToBuy,
            currencyToSellParam: currencyToSell,
            summToBuyParam: summToBuy,
            _csrf : getCookie("XSRF-TOKEN")
        };
        $scope.operation.$setPristine(true);
        $scope.operation.$setUntouched(true);
        Operation.save(operationData, function(response) {
            if(response.userOperations != undefined) {
                $scope.message.userOperations = response.userOperations;
                $scope.message.currencyBalances = response.currencyBalances;
                $scope.operation.showError = false;
            }
            else {
                $scope.operation.showError = true;
            }
        });
    };

    $scope.calculateCost = function(){
        $scope.exchangeCurrencyError = '';
        var currenciesToExchange = $scope.sell+"/"+$scope.buy;
        angular.forEach($scope.currenciesRates, function(value, key){
            if(currenciesToExchange == key) {
                $scope.exchangeCost = $scope.summ * value;
            }
            else {
                if($scope.sell == 0 || $scope.sell == undefined ) {
                    $scope.exchangeCost = "";
                    $scope.exchangeCost += "Валюта продажи не выбрана! ";
                    $scope.operation.$invalid = true;
                }
                if($scope.buy == 0 || $scope.buy == undefined) {
                    $scope.exchangeCost += "Валюта покупки не выбрана!";
                    $scope.operation.$invalid = true;
                }
                if($scope.sell == $scope.buy) {
                    $scope.exchangeCost = "";
                    $scope.exchangeCurrencyError = "Выберите валюты для обмена корректно!";
                    $scope.operation.$invalid = true;
                }
            }
        });
    }
});

app.controller('loginController', function($scope, $http){
    $http.get('/login/').then(function(response) {
        $scope.message = response.data;
        $scope.userLogged = userControllerScope.loggedUserName;
    })
});

function getCookie(name) {
    var cookie = " " + document.cookie;
    var search = " " + name + "=";
    var setStr = null;
    var offset = 0;
    var end = 0;
    if (cookie.length > 0) {
        offset = cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = cookie.indexOf(";", offset)
            if (end == -1) {
                end = cookie.length;
            }
            setStr = unescape(cookie.substring(offset, end));
        }
    }
    return(setStr);
}