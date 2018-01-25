<%--
  Created by IntelliJ IDEA.
  User: ccleveland
  Date: 1/16/18
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@	page import="java.util.*,java.text.*"%>
<!DOCTYPE html>
<html>
  <head>
      <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    <title>Cmd Servlet Home</title>
  </head>
  <body>
  <img src="http://www.clipartbest.com/cliparts/Kcj/e9B/Kcje9BRKi.gif" alt="flame gif">
  <p>Click submit to lookup weather forecast by your zip code</p>
  <form action="${pageContext.request.contextPath}/CmdServlet?cmd=weather" method="post">
      Zip Code:<br>
      <input type="text" name="zip"value="53202">
      <br>
      <input type="submit" value="submit">
  </form>
  <p>Click submit to lookup location based on IP/hostname</p>
  <form action="${pageContext.request.contextPath}/CmdServlet?cmd=locator" method="post">
      IP Address or Hostname:<br>
      <input type="text" name="address"value="github.com">
      <br>
      <input type="submit" value="submit">
  </form>
  </body>
</html>
