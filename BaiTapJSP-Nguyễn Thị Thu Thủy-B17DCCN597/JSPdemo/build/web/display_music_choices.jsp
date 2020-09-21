<%-- 
    Document   : display_music_choices
    Created on : Sep 21, 2020, 11:10:19 AM
    Author     : Nguyen Thu Thuy 1608
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servlet Display Music Choices Servlet</title>
    </head>
    <body>
        <%
 // get parameters from the request
        String firstName =
        request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String[] musics = request.getParameterValues("music");
        %>
       <h1>Thanks for joining our email list, <%=lastName%> <%=firstName%></h1>
       <p>We'll use this email to notify you whenever we have new release for these types of music: <br>
           <% if(musics !=null){
               for(String music : musics){ %>
               <%=music%> <br>
           <%}
           }%>
    </body>
</html>
