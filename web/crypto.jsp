<%@ page import="java.util.Map" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.util.HashMap" %>
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
    <title>Crypto Prices</title>
</head>
<body>
<h1>Crypto Data of <%=request.getParameter("currencies")%>
</h1>
<%Map<String, String> data = (Map<String, String>) request.getAttribute("data");%>
<Table>
    <%
        if (data != null) {
            for (Map.Entry<String, String> e : data.entrySet()) {
    %>
    <tr>
        <td>
            <%= e.getKey()%>
        </td>
        <td>
            <%= e.getValue()%>
        </td>
    </tr>
    <%
        }
    } else {
    %><p>Weather is unavailable</p>
    <%
        }
    %>
</Table>
</body>
</html>
