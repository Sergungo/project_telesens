package ua.golenko.security.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.golenko.security.dao.UserDAO;
import ua.golenko.security.model.User;
import ua.golenko.security.utils.AppUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO dao;

	public LoginServlet() {
		super();
		dao = UserDAO.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User userAccount = UserDAO.findUser(username, password);

		if (userAccount == null) {

			String errorMessage = "Invalid Username or Password";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
			return;
		}

		AppUtils.storeLoginedUser(request.getSession(), userAccount);

		if (userAccount != null) {

			HttpSession session = request.getSession(true);
			session.setAttribute("loginedUser", userAccount);
			response.sendRedirect(request.getContextPath() + "/userInfo"); // logged-in page
		}
	}
}