<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    	<link href="style.css" rel="stylesheet" type="text/css">
        <script>
            var app = angular.module('OneApp', []);
            app.controller('controllerDettaglioEroe', function($scope, $http)
            {
                $scope.eroe = [{nome:'Saitama', cognome:'Oppai',foto:'', vita:'100', attacco:'10', stamina:'10', efficacia:'10'}]


               $scope.dettaglioEroe = function()
                {
                    $scope.mostra = 'eroe';

                    $http.post("/dettaglioEroe").then(function(response){
                    $scope.eroe = response.data;
                    console.log($scope.eroe);
                });
                
            }
            });
        </script>
    </head>

    <body ng-app = "OneApp" ng-controller = "controllerDettaglioEroe">
        <span><img src="https://i.ytimg.com/vi/D-PnRiH7fPg/maxresdefault.jpg" width="300px" height="200px"/></span>
        <span class = "eroi" ng-repeat = "x in eroi">
            <b>Nome:</b> {{x.nome}}<br>
            <b>Cognome:</b> {{x.cognome}}<br>
            <b>Vita:</b> {{x.vita}}<br>
            <b>Attacco:</b> {{x.attacco}}<br>
            <b>Stamina:</b> {{x.stamina}}<br>
            <b>Efficacia:</b> {{x.efficacia}}<br><br>
            <b>Storia:</b><br>
            <div>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

            </div>
        </span>
    </body>
</html>