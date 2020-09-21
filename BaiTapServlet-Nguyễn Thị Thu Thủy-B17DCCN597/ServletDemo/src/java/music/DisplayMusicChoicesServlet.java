/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class DisplayMusicChoicesServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
    // get parameters from the request
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String emailAddress = request.getParameter("emailAddress");
    String[] musics = request.getParameterValues("music");
   
        response.setContentType("text/html;charset=UTF-8");
        // send response to browser
        PrintWriter out = response.getWriter(); 
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Display Music Choices Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Thanks for joining our email list, "+lastName+" "+firstName+"</h1>");
            out.println("<p>We'll use this email to notify you whenever we have new release for these types of music: <br>");
            for (String music : musics) {
            out.println(music+"<br>");
            }
            out.println("</body>");
            out.println("</html>");       
    out.close();
     } 
}
