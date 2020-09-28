<!DOCTYPE HTML PUBLIC
 "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>Murach's Java Servlets and JSP</title>
</head>
<body>
<%@ page import="business.User" %>
<%
 // get attributes from the request
 User user = (User) request.getAttribute("user");
 String message =
 (String) request.getAttribute("message");
 // handle null values
 if (user == null) user = new User();
 if (message == null) message = "";
%>
<h1>Join our email list</h1>
<p>To join our email list, enter your name and
 email address below. <br>
 Then, click on the Submit button.</p>
<p><i><%= message %></i></p>
<form action="addToEmailListServlet" method="post">
<table cellspacing="5" border="0">
<tr>
 <td align="right">First name:</td>
 <td><input type="text" name="firstName"
 value="<%= user.getFirstName() %>"></td>
</tr>
<tr>
 <td align="right">Last name:</td>
 <td><input type="text" name="lastName"
 value="<%= user.getLastName() %>"></td>
</tr>
<tr>
 <td align="right">Email address:</td>
 <td><input type="text" name="emailAddress"
 value="<%= user.getEmailAddress() %>"></td>
</tr>
<tr>
 <td></td>
 <td><br><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</body>
</html>