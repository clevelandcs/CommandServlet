<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <title>Crypto Prices</title>
    <link rel="stylesheet" type="text/css" href="menu.css">
</head>
<body>
<ul>
    <li><a class="active" href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/CmdServlet?cmd=index&menu=weather">Weather</a></li>
    <li><a href="${pageContext.request.contextPath}/CmdServlet?cmd=index&menu=geolocation">Geolocation</a></li>
    <li><a href="${pageContext.request.contextPath}/CmdServlet?cmd=index&menu=crypto">Crypto</a></li>
</ul>
<h1>Crypto Data of <%=request.getParameter("currencies")%>
</h1>
<%Map<String, HashMap> data = (Map<String, HashMap>) request.getAttribute("data");%>
<Table>
    <thead>
    <tr>
        <td>Currency</td>
        <td>Value in BTC</td>
        <td>Value in USD</td>
    </tr>
    </thead>
    <%
        if (data != null) {
            for (Map.Entry<String, HashMap> e : data.entrySet()) {
    %>
    <tr>
        <td>
            <%= e.getKey()%>
        </td>
        <%
            Map<String, String> subData = (Map<String, String>) e.getValue();
            for (Map.Entry<String, String> o : subData.entrySet()) {
        %>
        <td>
            <%= o.getValue()%>
        </td>
        <%
                }
            }
        } else {
        %><p>Weather is unavailable</p>
        <%
            }
        %>
    </tr>
</Table>
</body>
</html>
