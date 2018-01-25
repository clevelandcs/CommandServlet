<%@ page import="java.util.Map" %><%--
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
    <title>Internet Geolocation</title>
</head>
<body>
<h1>Geolocation Data of <%=request.getParameter("address")%>
</h1>
<Table>
    <%
        Map<String, String> data = (Map<String, String>) request.getAttribute("data");
    %>
    <%
        if (data != null) {
            String[] labels = (String[]) request.getAttribute("labels");
            for (int i = 0; i < labels.length; i++) {
    %>
    <tr>
        <td>
            <%= labels[i]%>
        </td>
        <td>
            <%= data.get(labels[i])%>
        </td>
    </tr>
    <% }
    } else {
    %><p>Geolocation is unavailable</p>
    <%
        }
    %>
</Table>
</body>
</html>
