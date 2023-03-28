<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
.myDiv {
  border: 5px outset red;
  background-color: Dark blue;    
  text-align: center;
 background-image: url("src/main/resources/market.jpg");
  height:300px ;
  
}
</style>
</head>

<body>
<h1 style =text-align:center> Welcome to the Trade Peak </h1>



<div class="myDiv">
 
<form action="/register">

<input style= font-size:30px type="submit" value="Register"/> 

</form>
 <br>
 
<form action="/login">

<input style= font-size:30px type="submit" value="Login"/> 

</form>
</div>
</body>
</html>