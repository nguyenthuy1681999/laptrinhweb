<%@ page import="business.*, data.*, java.util.Date" %>
<%
 // get parameters from the request
 String firstName = request.getParameter("firstName");
 String lastName = request.getParameter("lastName");
 String emailAddress =
 request.getParameter("emailAddress");
 // get a relative file name
 ServletContext sc = this.getServletContext();
 String path =
 sc.getRealPath("/WEB-INF/EmailList.txt");
 // use regular Java objects
 User user =
 new User(firstName, lastName, emailAddress);
 UserIO.add(user, path);
%>
<p>This email address was added to our list on
 <%= new Date() %></p>