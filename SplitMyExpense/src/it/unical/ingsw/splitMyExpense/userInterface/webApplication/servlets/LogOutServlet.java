package it.unical.ingsw.splitMyExpense.userInterface.webApplication.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doJob(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doJob(request, response);
	}

	private void doJob(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("utente");
	}

}
