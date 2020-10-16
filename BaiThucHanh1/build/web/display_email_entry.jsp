<%@ include file="header.html"%>
<h1>Thanks for joining our email list</h1>

<p>Here is the information that you entered:</p>

<%@ page import="business.User"%>
<% 
	List<User> users = (List<User>)request.getAttribute("users");
	User user = (User) request.getAttribute("user"); 
%>
<form action="addToEmailList" method="post">
     	<table cellspacing="5" border="1">
     	<tr>
     		<td align="right"> Search user email<br></td>
     	</tr>
     	<tr>
     		<td>
     			<input type="text" name="emailForSearch">
     		</td>
     		<td>
     			<input type ="submit" value="Search">
     		</td>
     	</tr>
     	</table>
     </form>
<table cellspacing="5" cellpadding="5" border="1">
	<%
    	if (users != null){
    		for (User u : users) {
    			out.println("<tr>");
    			
    			out.println("<td align=\"right\">First name:</td>");
    			out.println("<td>"+ u.getFirstName()+"</td>");
    			
    			out.println("<td align=\"right\">Last name:</td>");
    			out.println("<td>"+ u.getLastName()+"</td>");
    			
    			out.println("<td align=\"right\">Email Address:</td>");
    			out.println("<td>"+ u.getEmailAddress()+"</td>");
    			out.println("</tr>");
    		}
    	} else {
    		out.println("<tr>");
			out.println("<td align=\"right\">First name:</td>");
			out.println("<td>"+ user.getFirstName()+"</td>");
			
			out.println("<td align=\"right\">Last name:</td>");
			out.println("<td>"+ user.getLastName()+"</td>");
			
			out.println("<td align=\"right\">Email Address:</td>");
			out.println("<td>"+ user.getEmailAddress()+"</td>");
			out.println("</tr>");
    	}
    %>
	
</table>

<p>
	To enter another email address, click on the Back <br> button in
	your browser or the Return button shown <br> below.
</p>

<form action="index.jsp" method="post">
	<input type="submit" value="Return">
</form>
<%@ include file="footer.jsp"%>
