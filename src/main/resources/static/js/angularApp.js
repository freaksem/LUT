var app = angular.module('exchangeApp', ['ngRoute']);
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
app.controller('userController', function($scope, $http, $window){
    $http.get('/api/user/').then(function(response) {
        $scope.message = response.data;
        userControllerScope = response.data;
    });
    $scope.logout = function($location) {
        $http.post('logout', {}).finally(function(){
            $window.location.href="/";
        })
    }
});

app.controller('loginController', function($scope, $http){
    $http.get('/login/').then(function(response) {
        $scope.message = response.data;
        $scope.userLogged = userControllerScope.loggedUserName;
    })
});