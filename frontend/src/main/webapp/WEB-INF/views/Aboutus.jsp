<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--   -->
<html>
<head>
<title>SignaturePendent Aboutus</title>
<link rel="icon" href="resources\images\1K0vLh.jpg" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AboutUs</title>
<style>
* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: Arial;
    font-size: 17px;
}

.container {
    position: relative;
    max-width: 2000 px;
    margin: 0 auto;
}

.container img {vertical-align: middle;}

.container .content {
    position: absolute;
    top: 0;
    background: "#FFFFFF"; /* Black background with transparency */
    color: #f1f1f1;
    width: 100%;
    padding: 20px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" >
<img src="resources\images\download.jpg" style="width:100%;">
<div class="content">
<p>
This is a marketplace with a mission to deliver an outstanding shopping experience and offer a good brand of all varieties of shoes for  your favourite . We empower retailers to build individual relationships and go the extra mile for their customers. We are located in India. It is one of the growing web site for purchasing favourite shoes.
</p>
<p>
Our focus is more than  just a transaction experience to consumers. We encourage merchants to share great ideas and connect with shoppers with quality product, reviews, and content to ensure long lasting relationships with customers. 
</p>
<p>
We take privacy and security very seriously. Every transaction is protected and your information is kept on password protected, limited access servers. Wherever possible and applicable, our servers reside behind a corporate fire wall that maintains access control to the system from both our internal network and the Internet. 
</p>
<jsp:include page="footer.jsp"/>
</body>
</html>