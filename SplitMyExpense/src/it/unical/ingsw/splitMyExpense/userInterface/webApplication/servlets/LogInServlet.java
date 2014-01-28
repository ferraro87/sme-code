package it.unical.ingsw.splitMyExpense.userInterface.webApplication.servlets;

import it.unical.ingsw.splitMyExpense.domain.Utente;
import it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.ConnectionFactory;
import it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.DataAccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		doJob(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		doJob(request, response);
	}

	private void doJob(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		DataAccess dataAccess = new DataAccess(
				connectionFactory.getConnection());
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Utente utente = null;
		try {
			utente = dataAccess.logIn(email, password);
		} catch (SQLException e) {
			out.print("ERROR: DataBase Error");
		} catch (IllegalArgumentException e) {
			out.print("ERROR: " + e.getMessage());
		}
		if (utente != null) {
			HttpSession session = request.getSession();
			session.setAttribute("utente", utente);
		}
	}

}
