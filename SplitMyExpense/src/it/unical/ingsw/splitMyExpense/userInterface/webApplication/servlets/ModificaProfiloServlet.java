package it.unical.ingsw.splitMyExpense.userInterface.webApplication.servlets;

import it.unical.ingsw.splitMyExpense.domain.Utente;
import it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.ConnectionFactory;
import it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.DataAccess;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModificaProfiloServlet extends HttpServlet {
	
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

		String fotoNuova = request.getParameter("fotoNuova");
		String passwordNuova = request.getParameter("passwordNuova");
		String nomeNuovo = request.getParameter("nomeNuovo");

		int id=((Utente)request.getSession().getAttribute("utente")).getId();
		String email=((Utente)request.getSession().getAttribute("utente")).getEmail();
		
		Utente utente= dataAccess.updateUtente(id, email, passwordNuova, nomeNuovo, fotoNuova);
		
		request.getSession().setAttribute("utente",	utente);
	}

}
