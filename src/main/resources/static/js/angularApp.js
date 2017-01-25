var app = angular.module('exchangeApp', ['ngRoute']);
app.config(function($routeProvider){
   $routeProvider
       .when('/user',{
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
});

app.controller('userController', function($scope, $http){
    $http.get('/api/user/').then(function(response) {
        $scope = response.data;
    })
});

app.controller('loginController', function($scope, $http){
    $http.get('/login/').then(function(response) {
        $scope.message = response.data;
        //console.log($scope.message);
    })
});