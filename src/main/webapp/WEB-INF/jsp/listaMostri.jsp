<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    	<link href="style.css" rel="stylesheet" type="text/css">
        <script>
            var app = angular.module('OneApp', []);
            app.controller('controllerListaMostri', function($scope, $http)
            { 
                $scope.listaEroe = function()
                {
                    $scope.mostra = 'mostri';

                    $http.get("/listaMostri").then(function(response){
                    $scope.eroe = response.data;
                    console.log($scope.eroe);
                });
                }
            )
        </script>
    </head>
    <body ng-app = "OneApp" ng-controller = "controllerListaMostri">
    <div class = "listaMostri" ng-repeat = "x in listaMostri">{{x}}</div>
    </body>
</html>