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

public class AggiungiUtenteToGruppoServlet extends HttpServlet {
	
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

		String emailNuovo = request.getParameter("emailNuovoUtente");
		int idGruppo = Integer.parseInt(request.getParameter("idGruppo"));

		Utente nuovo=dataAccess.searchUtente(emailNuovo);
		if(nuovo!=null){
			dataAccess.addAppartenenzaGruppo(nuovo.getId(),	idGruppo);
			out.print("'"+nuovo.getNome()+"' e' stato aggiunto al gruppo");
		}else{
			out.print("l'email '"+emailNuovo+"' non e' presente nel sistema");
		}
		
	}


}
