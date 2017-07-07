<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="style.css" rel="stylesheet" type="text/css">
        <style type="text/css">
        	body
			{
			    background-image: url("https://i.ytimg.com/vi/ZYq2unOcq-I/maxresdefault.jpg");
			    background-repeat: no-repeat;
			    background-size: cover;
			    color: white;
			}
			
			h1
			{
			    color: white;
			    font-family: Impact, Haettenschweiler, "Franklin Gothic Bold", Charcoal, "Helvetica Inserat", "Bitstream Vera Sans Bold", "Arial Black", "sans serif";
			}
			
			h2
			{
			    color: white;
			}
			
			h3
			{
			    color: white;
			    font-family: Impact, Haettenschweiler, "Franklin Gothic Bold", Charcoal, "Helvetica Inserat", "Bitstream Vera Sans Bold", "Arial Black", "sans serif";  
			}
			
			span
			{
			    color: white;
			}
			
			.btn {
			  background: #3498db;
			  background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
			  background-image: -moz-linear-gradient(top, #3498db, #2980b9);
			  background-image: -ms-linear-gradient(top, #3498db, #2980b9);
			  background-image: -o-linear-gradient(top, #3498db, #2980b9);
			  background-image: linear-gradient(to bottom, #3498db, #2980b9);
			  -webkit-border-radius: 28;
			  -moz-border-radius: 28;
			  border-radius: 28px;
			  font-family: Impact, Haettenschweiler, "Franklin Gothic Bold", Charcoal, "Helvetica Inserat", "Bitstream Vera Sans Bold", "Arial Black", "sans serif";
			  color: #ffffff;
			  font-size: 20px;
			  padding: 10px 20px 10px 20px;
			  text-decoration: none;
			}
			
			.btn:hover {
			  background: #3cb0fd;
			  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
			  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
			  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
			  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
			  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
			  text-decoration: none;
			}
			
			.eroi{
			   font-size: 20px;
			   font-family: Impact, Charcoal, sans-serif;
			}
			
			.form-group
			{
			    color: white;
			}
        </style>
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