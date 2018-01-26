<%--
  Created by IntelliJ IDEA.
  User: ccleveland
  Date: 1/16/18
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@    page import="java.util.*,java.text.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    <title>Cmd Servlet Home</title>
    <link rel="stylesheet" type="text/css" href="menu.css">
</head>
<body>
<img src="http://www.clipartbest.com/cliparts/Kcj/e9B/Kcje9BRKi.gif" alt="flame gif">
<ul>
    <li><a class="active" href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/CmdServlet?cmd=index&menu=weather">Weather</a></li>
    <li><a href="${pageContext.request.contextPath}/CmdServlet?cmd=index&menu=geolocation">Geolocation</a></li>
    <li><a href="${pageContext.request.contextPath}/CmdServlet?cmd=index&menu=crypto">Crypto</a></li>
</ul>
<%
    String menu = (String) request.getAttribute("menu");
    if (menu != null) {
        if (menu.equalsIgnoreCase("weather")) {
%>
<p>Click submit to lookup weather forecast by your zip code</p>
<form action="${pageContext.request.contextPath}/CmdServlet?cmd=weather" method="post">
    Zip Code:<br>
    <input type="text" name="zip" value="53202">
    <br>
    <input type="submit" value="submit">
</form>
<%} else if (menu.equalsIgnoreCase("geolocation")) {%>
<p>Click submit to lookup location based on IP/hostname</p>
<form action="${pageContext.request.contextPath}/CmdServlet?cmd=locator" method="post">
    IP Address or Hostname:<br>
    <input type="text" name="address" value="github.com">
    <br>
    <input type="submit" value="submit">
</form>
<%} else if (menu.equalsIgnoreCase("crypto")) {%>
<p>Click submit to lookup location based on IP/hostname</p>
<form action="${pageContext.request.contextPath}/CmdServlet?cmd=crypto" method="post">
    Enter Crypto currency tickers separated by commas:<br>
    <input type="text" name="currencies" value="BTC,ETH">
    <br>
    <input type="submit" value="submit">
</form>
<%
    }
} else {
%>
<p>Please select an item from the menu above to proceed</p>
<%
    }
%>
</body>
</html>
