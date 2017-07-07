<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
		<script>
			var app = angular.module('OneApp', []);
				app.controller('controllerLogin', function($scope, $http)
				{
					$scope.login = function(email, password){
						console.log("Email: " + email + ", password: " + password);
						$http.post("/login/"+email+"/"+password).then(function(response){
							$scope.login = response.data;
							console.log($scope.login);

							if($scope.login >= 0 && $scope.login < 3)
								alert("si");
							else
								alert("no");
						});
					};
				});
		</script>


        <!--
		<style>

			body {
				background: red;
			}

			.wrapper {
			margin-top: 80px;
			margin-bottom: 40px;
			}

			.form-signin {
				max-width: 380px;
				padding: 15px 35px 40px;
				margin: 0 auto;
				background-color: #fff;
				border: 1px solid rgba(0,0,0,0.1);
				border-radius:12px;
			}
			.form-control {
					position: relative;
					top: 10px;
					font-size: 16px;
					height: auto;
					padding: 10px;

				}

				#username {

					margin-bottom: 10px;
				}

				#password {
					margin-bottom: 20px;
					top: 15px;
				}

		</style>
        -->

	</head>
	<body ng-app="accesso" ng-controller="controller" align="center">

		<header>
			<h1>OnePunchApp</h1>
		</header>
		<div class="wrapper" >
			<div class="form-signin">
			<h2 class="form-signin-heading">Accedi</h2>
			<center>
				<form action="/login" method="post" modelAttribute="res" >
					<table>
						<tr>
							<td>Email:</td>
							<td><input type="text" ng-model="email" id="surname" class="form-control" placeholder="Email Address" required="" autofocus="" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="text" ng-model="password" id="password" class="form-control" placeholder="Password" required=""/></td>
						</tr>
						<tr><td></td><!-- ng-click="login(username, password)" -->
						<td><input class="btn btn-primary btn-block" type="submit" value="Login" ></input></td>
						</tr>
					</table>
				</form>

				<p class="create-account-callout mt-3">
			        Nuovo Utente? <a href="./formRegistrazione.jsp">CAZZI TUOI EHEHEH <!-- ng-click="formRegistrazione()"  -->
			    </p>
			</center>
			</div>
		</div>
	</body>
</html>
