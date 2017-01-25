var app = angular.module('exchangeApp', ['ngRoute']);
app.config(function($routeProvider){
   $routeProvider
       .when('/user',{
           templateUrl: '../../templates/user.html',
           controller: 'userController'
       })
       .when('/login', {
           templateUrl: '../../templates/login.html',
           controller: 'loginController'
       })
       .otherwise(
           {redirectTo: '/'}
       );
});

app.controller('userController', function($scope, $http){
    $http.get('/user/').then(function(response) {
        $scope.message = response.data;
        console.log($scope.message);
    })
});

app.controller('loginController', function($scope, $http){
    $http.get('/login/').then(function(response) {
        $scope.message = response.data;
        console.log($scope.message);
    })
});