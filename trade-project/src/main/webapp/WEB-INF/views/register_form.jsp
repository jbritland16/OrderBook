<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Registration form </h1>



<form action="/registerProcess" method="POST">



User Name : <input type="text" name="userName"/><br>

User Password : <input type="password" name="userPassword"/> <br>

User Email : <input type="email" name="userEmail"/> <br>



<input type="submit" value="REGISTER"/> 
</form>
</body>
</html>