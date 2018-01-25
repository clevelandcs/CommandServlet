<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: ccleveland
  Date: 1/17/18
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <title>Weather Forecast</title>
</head>
<body>
<h1>Your 4-Day Forecast</h1>
<Table>
    <%
        Map<String, ArrayList> weather = (Map<String, ArrayList>) request.getAttribute("weather");
    %>
    <%
        if (weather != null) {
            for (int i = 0; i < weather.get("DayOfWeek").size(); i++) {
                for (Map.Entry<String, ArrayList> e : weather.entrySet()) {
    %>
    <tr>
        <td>
            <%= e.getKey()%>
        </td>
        <td>
            <%= e.getValue().get(i)%>
        </td>
    </tr>
    <%          }
            }
        } else {
    %><p>Weather is unavailable</p>
    <%
        }
    %>
</Table>
</body>
</html>
