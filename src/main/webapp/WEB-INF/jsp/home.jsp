<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
        <script>
	        var app = angular.module('OneApp',[]);
	        app.controller('homeController', $scope, $http){
	            
	        }
        </script>
    </head>
    <body ng-app = "OneApp" ng-controller = "homeController">
        <h1>
            <center>
                <div>Benvenuto su OnePunchAPP!</div>
            </center>
        </h1>

        <center>
            <div>
                <section>
                    <a class = "btn" href = "./listaEroi.jsp"><btn>Lista Eroi</btn></a>
                    <a class = "btn" href = "./listaMostri.jsp"><btn>Lista Mostri</btn></a>
                    <a class = "btn" href = "./login.jsp"><btn>Login</btn></a>
                </section>
            </div>
        </center>

        <br><br>

        <center>
            <div>OnePunchApp nasce dall'idea di un gruppo di ragazzi, tra cui uno di caserta e ci scusiamo subito in anticipo per questo.</div>
        </center>

        <div>
            <center>
                <h3>Le regole</h3>
            </center>
        </div>

        <center><div>SCRIVERE LE REGOLE DEL GIOCO</div></center>
    </body>
</html>