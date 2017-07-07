<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>REGISTRATI</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
        <script>
            var app = angular.module('registrazione', []);
                app.controller('controllerFormRegistrazione', function($scope, $http)
                {
                    $scope.nome = "";    
                })
        </script>
    </head>
    <body>
        <h2>Registrati a OneAPP brutto stronzo</h2>
        <center><a href="./home.jsp">HOME</a></center>
    <form name="form" ng-submit="vm.register()" role="form">
        <div class="form-group" ng-class="{ 'has-error': form.firstName.$dirty && form.firstName.$error.required }">
            <label for="username">Nome</label>
            <input type="text" style = "width:40%" name="nome" id="nome" class="form-control" ng-model="vm.user.nome" required />
            
        </div>
        <div class="form-group" ng-class="{ 'has-error': form.cognome.$dirty && form.cognome.$error.required }">
            <label for="username">Cognome</label>
            <input type="text" style = "width:40%" name="cognome" id="Text1" class="form-control" ng-model="vm.user.cognome" required />
            
        </div>
        <div class="form-group" ng-class="{ 'has-error': form.username.$dirty && form.username.$error.required }">
            <label for="username">Username</label>
            <input type="text" style = "width:40%" name="username" id="username" class="form-control" ng-model="vm.user.username" required />
         
        </div>
        <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
            <label for="password">Password</label>
            <input type="password" style = "width:40%" name="password" id="password" class="form-control" ng-model="vm.user.password" required />
        
        </div>
        <div class="form-actions">
            <button type="submit" ng-disabled="form.$invalid || vm.dataLoading" class="btn btn-primary">Registrati!</button>
            <a href="#!/login" class="btn btn-link">Annulla</a>
        </div>
    </form>

    </body>
</html>