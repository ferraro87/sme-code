package it.unical.ingsw.splitMyExpense.technicalServices.aggregateData;

import it.unical.ingsw.splitMyExpense.domain.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListaSpesaAggregata {

	ListaSpesa lista;
	Map<Prodotto, List<Utente>> data = new HashMap<Prodotto, List<Utente>>();

	public ListaSpesaAggregata(ListaSpesa ls) {
		lista = ls;
	}
	
	public ListaSpesa getListaSpesa(){
		return lista;
	}

	public void addPartecipazione(Prodotto p, Utente u) {
		if(!data.containsKey(p)){
			List<Utente> utenti=new LinkedList<Utente>();
			utenti.add(u);
			data.put(p, utenti);
		}else{
			data.get(p).add(u);
		}
	}
	
	public Set<Prodotto> getListaProdotti(){
		return data.keySet();
	}
	
	public List<Utente> getUtentiAssociati(Prodotto p){
		return data.get(p);
	}

}
