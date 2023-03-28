<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Login</title>
</head>
<body>
<h1>  Thank you for registering, now you can log on</h1>


<form action="/login" method="POST">



User Name : <input type="text" name="userName"/><br>

User Password : <input type="password" name="userPassword"/> <br>


<input type="submit" value="Login"/> 
</form>
</body>
</html>