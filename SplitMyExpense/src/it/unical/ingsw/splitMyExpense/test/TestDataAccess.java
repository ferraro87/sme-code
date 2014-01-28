package it.unical.ingsw.splitMyExpense.test;

import it.unical.ingsw.splitMyExpense.domain.Utente;
import it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.ConnectionFactory;
import it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.DataAccess;

public class TestDataAccess {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataAccess da=new DataAccess(ConnectionFactory.getConnection());
		
		/*Aggiungi Utente
		Utente u=da.addUtente("ciccio.milea@gmail.com", "ciccio.milea110287", "CiccioMilea");
		System.out.println(u);
		Utente u=da.addUtente("giuseppe.ferraro87@gmail.com", "123456", "PeppeFerraro");
		System.out.println(u);
		System.out.println(da.searchUtente("ciccio.milea@gmail.com"));
		System.out.println(da.logIn("ciccio.milea@gmail.com", "ciccio.milea110287"));
		System.out.println(da.logIn("cicilea@gmail.com", "ciccio.mile287"));
		System.out.println(da.updateUtente(15, "ciccio.milea@gmail.com", "110287", "CiccioMilea", "img/3/foto.png"));
		System.out.println(*/da.addGruppoSpesa(3, "GruppoDue", "Gruppo Spesa Del Gruppo Due");/*);
		da.addAppartenenzaGruppo(4, 2);
		*/da.addAppartenenzaGruppo(4, 3);/*
		System.out.println(da.addListaSpesa(2));
		System.out.println(da.setDescrizioneLista(7, "lista spesa numero sette"));
		da.remListaSpesa(7);
		System.out.println(da.setAperturaLista(8, 0));
		System.out.println(da.addProdottoToLista(3, 8, "carta igenica"));
		da.remPartecipazioneProdotto(3, 3);
		System.out.println(da.addScontrino(2, 3));
		System.out.println(da.addProdottoToScontrino(1, "pompelmo", "0.35"));
		da.associaScontrinoListaSpesa(1, 8);
		System.out.println(da.chiudiScontrino(1));
		System.out.println(da.getScontriniGruppoSpesa(2));
		System.out.println(da.getListeSpesaGruppoSpesa(2));
		System.out.println(da.getStatisticaUtente(3));
		System.out.println(da.addStatistica(11, 11, 11, "2013-12-09 18:38:39", "15.33"));
		*/
		
	}

}
