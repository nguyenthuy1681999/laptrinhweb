/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.User;
import data.UserIO;

/**
 *
 * @author Admin
 */
public class AddToEmailListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		ServletConfig config = this.getServletConfig();
		String relativePath = config.getInitParameter("relativePathToFile");
		ServletContext context = getServletContext();
		String path = context.getRealPath(relativePath);
		
		// get parameters from the request
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("emailAddress");
		
		String emailSearch = request.getParameter("emailForSearch");
		String url = "";
		if (emailSearch == null) {			
			// use regular Java objects to write the data to a file
			User user = new User(firstName, lastName, emailAddress);

			// validate the parameters
			String fmessage = "", lmessage = "", emessage = "";
			if (firstName.length() == 0) {
				fmessage = "Please fill out the first name";
				url = "/index.jsp";
			}
			if (lastName.length() == 0) {
				lmessage = "Please fill out the last name";
				url = "/index.jsp";
			}
			if (emailAddress.length() == 0) {
				emessage = "Please fill out the email address";
				url = "/index.jsp";
			}
			if ((fmessage.length() == 0) && (lmessage.length() == 0) && (emessage.length() == 0)) {
				System.out.println(path);
				UserIO.add(user, path);
				
				List<User> users = UserIO.getAll(path);
				if (users != null) {
					request.setAttribute("users", users);
				}
				url = "/display_email_entry.jsp";
			}
			request.setAttribute("user", user);
			request.setAttribute("fmessage", fmessage);
			request.setAttribute("lmessage", lmessage);
			request.setAttribute("emessage", emessage);
		} else {
			User user = UserIO.getByEmail(emailSearch, path);
			if (user != null) {
				firstName = user.getFirstName();
				lastName = user.getLastName();
				emailAddress = user.getEmailAddress();
				request.setAttribute("user", user);
				url = "/display_email_entry.jsp";
			} else {
				url = "/index.jsp";
			}
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
