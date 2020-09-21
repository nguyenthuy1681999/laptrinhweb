/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.User;
import data.UserIO;
import javax.servlet.ServletContext;
/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class AddToEmailListServlet extends HttpServlet
{
 protected void doPost(
 HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException
 {
 // get parameters from the request
 String firstName = request.getParameter("firstName");
 String lastName = request.getParameter("lastName");
 String emailAddress = request.getParameter("emailAddress");
 // get a relative file name
 ServletContext sc = getServletContext();
 String path = sc.getRealPath("/WEB-INF/EmailList.txt");
 User user = new User(firstName, lastName, emailAddress);
 UserIO.add(user, path);

 // send response to browser
 response.setContentType("text/html;charset=UTF-8");
 PrintWriter out = response.getWriter();
 out.println(
 "<!doctype html public \"-//W3C//DTD HTML 4.0 "
 + " Transitional//EN\">\n"
 + "<html>\n"
 + "<head>\n"
 + " <title>Murach's Java Servlets and JSP</title>\n"
 + "</head>\n"
 + "<body>\n"
 + "<h1>Thanks for joining our email list</h1>\n"
 + "<p>Here is the information that you entered:</p>\n"
 + " <table cellspacing=\"5\" cellpadding=\"5\" "
 + " border=\"1\">\n"
 + " <tr><td align=\"right\">First name:</td>\n"
 + " <td>" + firstName + "</td>\n"
 + " </tr>\n"
+ " <tr><td align=\"right\">Last name:</td>\n"
 + " <td>" + lastName + "</td>\n"
 + " </tr>\n"
 + " <tr><td align=\"right\">Email address:</td>\n"
 + " <td>" + emailAddress + "</td>\n"
 + " </tr>\n"
 + " </table>\n"
 + "<p>To enter another email address, click on the "
 + "Back <br>\n"
 + "button in your browser or the Return button shown <br>\n"
 + "below.</p>\n"
 + "<form action=\"join_email_list.html\" "
 + " method=\"post\">\n"
 + " <input type=\"submit\" value=\"Return\">\n"
 + "</form>\n"
 + "</body>\n"
 + "</html>\n");

 out.close();
 } 
protected void doGet(
 HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException
 {
 doPost(request, response);
 }
}