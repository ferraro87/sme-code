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

import org.apache.catalina.Session;

public class AbbandonaGruppoServlet extends HttpServlet {
	
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

		int idGruppo=Integer.parseInt(request.getParameter("idGruppo"));
		int idUtente=((Utente)request.getSession().getAttribute("utente")).getId();
		
		dataAccess.remAppartenenzaGruppo(idUtente, idGruppo);

	}


}
