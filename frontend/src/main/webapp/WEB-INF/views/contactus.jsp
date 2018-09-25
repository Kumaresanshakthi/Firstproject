<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--   -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
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
    max-width: 100%;
    margin: 0 auto;
}

.container img {vertical-align: middle;}
.address {
  margin-bottom: 1rem;
  font-style: sans-serif;
  line-height: inherit;
}
.container .content {
    position: absolute;
    top: 0;
    background: "#FFFFFF"; /* Black background with transparency */
    color: #000000;
    width: 100%;
    padding: 20px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
  <img src="resources\images\XX.jpg" style="width:40%;">
  <div class="content">
 <center><b> <l>Head Office:</l><br>
<l>THE  SIGNATURE PENDANT</l><br>
<l>C-5,"G"Block,</l><br>
<l>Bandra Kurla Complex,</l><br>
<l>Bandra(EAST),</l><br>
<l>Mumbai 400 051.</l><br>
<l>Ph:9092692804</l></b><br></center>
  
  </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>