<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
    font-family: Arial, Helvetica, sans-serif;
    background-color: black;
}

* {
    box-sizing: border-box;
}

/* Add padding to containers */
.container {
    padding: 16px;
    background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

/* Overwrite default styles of hr */
hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

.registerbtn:hover {
    opacity: 1;
}

/* Add a blue text color to links */
a {
    color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
    background-color: #f1f1f1;
    text-align: center;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>


 <form:form method="post" action="save" modelAttribute="signup">  
  <div class="container">
    <h1>signup</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
    
    <label for="name"><b>Name</b></label>
	<form:input type="text" path="UserName" placeholder="Enter UserName" name="name" />
	
	
    <label for="email"><b>Email</b></label>
    <form:input type="text" path="Email" placeholder="Enter Email" name="email" />

    <label for="psw"><b>Password</b></label>
    <form:input type="password" path="Password" placeholder="Enter Password" name="psw" />

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" />
    
    <label for="psw"><b>Mobile Number</b></label>
    <form:input type="password" path="PhoneNumber" placeholder="Enter your mobile no" name="psw" />

    
    <hr>
    <p>By creating an account you agree to our <a href="#">Terms and Privacy</a>.</p>

    <button type="submit" class="registerbtn">signup</button>
  </div>
  
  <div class="container signin">
    <p>Already have an account? <a href="#">Sign in</a></p>
  </div>
</form:form>
<jsp:include page="footer.jsp"/>
</body>
</html>
