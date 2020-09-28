package email;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import business.User;
import data.UserIO;
public class AddToEmailListServlet extends HttpServlet
{
 protected void doPost(HttpServletRequest request,
 HttpServletResponse response)
throws ServletException,
 IOException
 {
 // get parameters from the request
 String firstName =
 request.getParameter("firstName");
 String lastName = request.getParameter("lastName");
 String emailAddress =
 request.getParameter("emailAddress");
 // create the User object from the parameters
 User user =
 new User(firstName, lastName, emailAddress);
 // validate the parameters
 String message = "";
 String url = "";
 if (firstName.length() == 0 ||lastName.length() == 0 ||emailAddress.length() == 0)
 {
 message ="Please fill out all three text boxes.";
 url = "/join_email_list.jsp";
 }
 else
 {
 message = "";
 // get a relative file name

 ServletContext context = this.getServletContext();
 String custServEmail =
 context.getInitParameter("custServEmail");
 ServletConfig config = this.getServletConfig();
 String relativePath =
 config.getInitParameter("relativePathToFile");
 relativePath=context.getRealPath(relativePath);
 // use regular Java classes
 UserIO.add(user, relativePath);

 // store the User object in the request object
 // forward request and response objects to JSP page
 url = "/display_email_entry.jsp";
 }
 request.setAttribute("user", user);
 request.setAttribute("message", message);
 RequestDispatcher dispatcher =
 getServletContext().getRequestDispatcher(url);
 dispatcher.forward(request, response);

}
}