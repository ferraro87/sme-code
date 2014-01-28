package it.unical.ingsw.splitMyExpense.technicalServices.dataAccess;

import it.unical.ingsw.splitMyExpense.domain.GruppoSpesa;
import it.unical.ingsw.splitMyExpense.domain.ListaSpesa;
import it.unical.ingsw.splitMyExpense.domain.Prodotto;
import it.unical.ingsw.splitMyExpense.domain.Quota;
import it.unical.ingsw.splitMyExpense.domain.Scontrino;
import it.unical.ingsw.splitMyExpense.domain.Utente;
import it.unical.ingsw.splitMyExpense.technicalServices.aggregateData.ListaSpesaAggregata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DataAccess {

	private Connection connection;

	public DataAccess(Connection connection) {
		this.connection = connection;
	}

	public Utente searchUtente(String email) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from utente where email='" + email + "'";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				return new Utente(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5));
			} else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Utente searchUtente(int id) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from utente where idUtente='" + id + "'";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				return new Utente(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5));
			} else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Utente addUtente(String email, String password, String nome) {
		Utente giaPresente = searchUtente(email);
		if (giaPresente == null) {
			Statement statement;
			try {
				statement = connection.createStatement();
				String query = "insert into utente(email,password,nome) values('"
						+ email + "','" + password + "','" + nome + "')";
				// syso
				// System.out.println(query);
				statement.executeUpdate(query);
				query = "select MAX(idUtente) from utente";
				ResultSet result = statement.executeQuery(query);
				if (result.next()) {
					return new Utente(result.getInt(1), email, password, nome,
							"img/defaultUserImage.png");
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}

	public Utente logIn(String email, String password) throws SQLException {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from utente where email='" + email + "'";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				if (result.getString(3).equals(password)) {
					return new Utente(result.getInt(1), email, password,
							result.getString(4), result.getString(5));
				} else {
					throw new IllegalArgumentException("password errata");
				}
			} else {
				throw new IllegalArgumentException("email non trovata");
			}
		} catch (SQLException e) {
			throw e;
		}
		// return null;
	}

	public Utente updateUtente(int id, String email, String password,
			String nome, String foto) {
		if (searchUtente(id) != null) {
			Statement statement;
			try {
				statement = connection.createStatement();
				String query = "update utente set password='" + password
						+ "', nome='" + nome + "', foto='" + foto
						+ "' where idUtente='" + id + "'";
				statement.executeUpdate(query);
				return new Utente(id, email, password, nome, foto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}

	public List<Utente> getListaUtenti() {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from utente";
			List<Utente> utenti = new LinkedList<Utente>();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				utenti.add(new Utente(result.getInt(1), result.getString(2),
						"hidden", result.getString(4), result.getString(5)));
			}
			return utenti;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public GruppoSpesa getGruppo(int idGruppo) {
		GruppoSpesa gruppo = null;
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from gruppospesa where idGruppoSpesa='"
					+ idGruppo + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				gruppo = new GruppoSpesa(idGruppo, result.getString(2),
						result.getString(3), result.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gruppo;
	}

	public List<GruppoSpesa> getListaGruppiUtente(int idUtente) {
		List<GruppoSpesa> lista = new LinkedList<GruppoSpesa>();
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from gruppospesa where idGruppoSpesa in (select idGruppospesa from appartenenzagruppo where idUtente="
					+ idUtente + ")";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lista.add(new GruppoSpesa(result.getInt(1),
						result.getString(2), result.getString(3), result
								.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public GruppoSpesa addGruppoSpesa(int idUtente, String nome,
			String descrizione) {
		Statement statement;
		GruppoSpesa nuovoGruppo;
		try {
			statement = connection.createStatement();
			String query = "insert into gruppospesa(nome, descrizione) values('"
					+ nome + "','" + descrizione + "')";
			statement.executeUpdate(query);
			query = "select MAX(idGruppoSpesa) from gruppospesa";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovoGruppo = new GruppoSpesa(result.getInt(1), nome,
						descrizione, "'img/defaultGroupImage.png'");
			} else {
				return null;
			}
			query = "insert into appartenenzagruppo(idUtente,idGruppoSpesa) values('"
					+ idUtente + "','" + nuovoGruppo.getId() + "')";
			statement.executeUpdate(query);
			return nuovoGruppo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public GruppoSpesa addGruppoSpesa(int idUtente, String nome) {
		Statement statement;
		GruppoSpesa nuovoGruppo;
		try {
			statement = connection.createStatement();
			String query = "insert into gruppospesa(nome) values('" + nome
					+ "')";
			statement.executeUpdate(query);
			query = "select MAX(idGruppoSpesa) from gruppospesa";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovoGruppo = new GruppoSpesa(result.getInt(1), nome,
						"descrizione mancante", "'img/defaultGroupImage.png'");
			} else {
				return null;
			}
			query = "insert into appartenenzagruppo(idUtente,idGruppoSpesa) values('"
					+ idUtente + "','" + nuovoGruppo.getId() + "')";
			statement.executeUpdate(query);
			return nuovoGruppo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addAppartenenzaGruppo(int idUtente, int idGruppoSpesa) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "insert into appartenenzagruppo(idUtente,idGruppoSpesa) values('"
					+ idUtente + "','" + idGruppoSpesa + "')";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remAppartenenzaGruppo(int idUtente, int idGruppoSpesa) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "delete from appartenenzagruppo where idUtente='"
					+ idUtente + "' AND idGruppoSpesa='" + idGruppoSpesa + "'";
			// System.out.println(query);
			statement.executeUpdate(query);
			query="select count(*) from appartenenzagruppo where idGruppoSpesa='"+idGruppoSpesa+"'";
			ResultSet result=statement.executeQuery(query);
			result.next();
			if(result.getInt(1)==0){
				query="delete from statistiche where idGruppoSpesa='"+idGruppoSpesa+"'";
				statement.executeUpdate(query);
				query="delete from scontrino where idGruppoSpesa='"+idGruppoSpesa+"'";
				statement.executeUpdate(query);
				query="delete from listaspesa where idGruppoSpesa='"+idGruppoSpesa+"'";
				statement.executeUpdate(query);
				query="delete from gruppospesa where idGruppoSpesa='"+idGruppoSpesa+"'";
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ListaSpesa addListaSpesa(int idGruppo) {
		/*
		 * formato data "2013-05-08 17-59-50"
		 */
		Statement statement;
		ListaSpesa nuovaLista;
		try {
			statement = connection.createStatement();
			String query = "insert into listaspesa(idGruppoSpesa,dataCreazione) values('"
					+ idGruppo + "',NOW())";
			// System.out.println(query);
			statement.executeUpdate(query);
			query = "select MAX(idListaSpesa),dataCreazione from listaspesa";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovaLista = new ListaSpesa(result.getInt(1), idGruppo,
						result.getString(2));
			} else {
				return null;
			}
			return nuovaLista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ListaSpesa getListaSpesa(int idLista) {
		ListaSpesa lista = null;
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select * from listaspesa where idListaSpesa='"
					+ idLista + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				boolean aperta = true;
				if (result.getInt(4) == 0)
					aperta = false;
				if (result.getInt(4) == 1)
					aperta = true;
				lista = new ListaSpesa(idLista, result.getInt(2),
						result.getString(3), aperta, result.getString(5),
						result.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public ListaSpesa setDescrizioneLista(int idListaSpesa, String descrizione) {
		Statement statement;
		ListaSpesa ls;
		try {
			statement = connection.createStatement();
			String query = "update listaspesa set descrizione='" + descrizione
					+ "' where idListaSpesa='" + idListaSpesa + "'";
			statement.executeUpdate(query);
			query = "select * from listaspesa where idListaSpesa='"
					+ idListaSpesa + "'";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				ls = new ListaSpesa(idListaSpesa, result.getInt(2),
						result.getString(3), result.getBoolean(4),
						result.getString(5), descrizione);
			} else {
				return null;
			}
			return ls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void remListaSpesa(int idListaSpesa) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "delete from listaspesa where idListaSpesa='"
					+ idListaSpesa + "'";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ListaSpesa setAperturaLista(int idListaSpesa, int aperta) {
		Statement statement;
		ListaSpesa ls;
		try {
			statement = connection.createStatement();
			String query = "";
			if (aperta == 1) {
				query = "update listaspesa set aperta='" + aperta
						+ "', dataChiusura=NULL where idListaSpesa='"
						+ idListaSpesa + "'";
			} else {
				query = "update listaspesa set aperta='" + aperta
						+ "', dataChiusura=NOW() where idListaSpesa='"
						+ idListaSpesa + "'";
			}
			statement.executeUpdate(query);
			query = "select * from listaspesa where idListaSpesa='"
					+ idListaSpesa + "'";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				ls = new ListaSpesa(idListaSpesa, result.getInt(2),
						result.getString(3), result.getBoolean(4),
						result.getString(5), result.getString(6));
			} else {
				return null;
			}
			return ls;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Prodotto addProdottoToLista(int idUtente, int idListaSpesa,
			String nome) {
		Statement statement;
		Prodotto nuovoProdotto;
		try {
			statement = connection.createStatement();
			String query = "insert into prodotto(nome,idListaSpesa) values('"
					+ nome + "','" + idListaSpesa + "')";
			statement.executeUpdate(query);
			query = "select MAX(idProdotto) from prodotto";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovoProdotto = new Prodotto(result.getInt(1), nome,
						idListaSpesa);
			} else {
				return null;
			}
			addPartecipazioneProdotto(statement, idUtente,
					nuovoProdotto.getId());
			return nuovoProdotto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addPartecipazioneProdotto(int idUtente, int idProdotto) {
		addPartecipazioneProdotto(null, idUtente, idProdotto);
	}

	private void addPartecipazioneProdotto(Statement statement, int idUtente,
			int idProdotto) {
		try {
			if (statement == null) {
				statement = connection.createStatement();
			}
			String query = "insert into partecipazioneprodotto(idUtente,idProdotto) values('"
					+ idUtente + "','" + idProdotto + "')";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remPartecipazioneProdotto(int idUtente, int idProdotto) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "delete from partecipazioneprodotto where idUtente='"
					+ idUtente + "' AND idProdotto='" + idProdotto + "'";
			statement.executeUpdate(query);
			query = "select COUNT(idUtente) from partecipazioneprodotto where idProdotto='"
					+ idProdotto + "'";
			ResultSet result = statement.executeQuery(query);
			result.next();
			if (result.getInt(1) == 0) {
				query = "delete from prodotto where idProdotto='" + idProdotto
						+ "'";
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ListaSpesaAggregata getListaSpesaAggregata(int id) {
		ListaSpesa ls = getListaSpesa(id);
		ListaSpesaAggregata lsa = new ListaSpesaAggregata(ls);

		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "select prodotto.idProdotto, prodotto.nome, utente.foto, utente.nome, utente.idUtente from prodotto,utente,partecipazioneprodotto where prodotto.idListaSpesa='"
					+ id
					+ "' and prodotto.idProdotto=partecipazioneprodotto.idProdotto and partecipazioneprodotto.idUtente=utente.idUtente";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lsa.addPartecipazione(
						new Prodotto(result.getInt(1), result.getString(2), id),
						new Utente(result.getInt(5), "fake@fake.com", "fakepassword", result
								.getString(4), result.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsa;
	}

	public Scontrino addScontrino(int idGruppoSpesa, int idUtentePagante) {
		Statement statement;
		Scontrino nuovoScontrino;
		try {
			statement = connection.createStatement();
			String query = "insert into scontrino(dataEmissione,idGruppoSpesa,idUtentePagante) values(NOW(),'"
					+ idGruppoSpesa + "','" + idUtentePagante + "')";
			statement.executeUpdate(query);
			query = "select MAX(idScontrino),dataEmissione from scontrino";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovoScontrino = new Scontrino(result.getInt(1),
						result.getString(2), idGruppoSpesa, idUtentePagante);
			} else {
				return null;
			}
			return nuovoScontrino;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Prodotto addProdottoToScontrino(int idScontrino, String nome,
			String prezzo) {
		Statement statement;
		Prodotto nuovoProdotto;
		try {
			statement = connection.createStatement();
			String query = "insert into prodotto(nome,prezzo,idScontrino) values('"
					+ nome + "','" + prezzo + "','" + idScontrino + "')";
			statement.executeUpdate(query);
			query = "select MAX(idProdotto) from prodotto";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovoProdotto = new Prodotto(result.getInt(1), nome, prezzo,
						idScontrino);
			} else {
				return null;
			}
			return nuovoProdotto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void associaScontrinoListaSpesa(int idScontrino, int idListaSpesa) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "update scontrino set idListaSpesa='" + idListaSpesa
					+ "' where idScontrino='" + idScontrino + "'";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Scontrino chiudiScontrino(int idScontrino) {
		Statement statement;
		Scontrino nuovoScontrino;
		try {
			statement = connection.createStatement();
			String query = "update scontrino set aperto='0', dataChiusura=NOW() where idScontrino='"
					+ idScontrino + "'";
			statement.executeUpdate(query);
			query = "select * from scontrino where idScontrino='" + idScontrino
					+ "'";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				nuovoScontrino = new Scontrino(result.getInt(1),
						result.getString(2), result.getInt(3),
						result.getInt(4), result.getInt(5),
						result.getBoolean(6), result.getString(7));
			} else {
				return null;
			}
			return nuovoScontrino;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Scontrino> getScontriniGruppoSpesa(int idGruppoSpesa) {
		Statement statement;
		List<Scontrino> lista = new LinkedList<Scontrino>();
		try {
			statement = connection.createStatement();
			String query = "select * from scontrino where idGruppoSpesa='"
					+ idGruppoSpesa + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lista.add(new Scontrino(result.getInt(1), result.getString(2),
						result.getInt(3), result.getInt(4), result.getInt(5),
						result.getBoolean(6), result.getString(7)));
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<ListaSpesa> getListeSpesaGruppoSpesa(int idGruppoSpesa) {
		Statement statement;
		List<ListaSpesa> lista = new LinkedList<ListaSpesa>();
		try {
			statement = connection.createStatement();
			String query = "select * from listaspesa where idGruppoSpesa='"
					+ idGruppoSpesa + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lista.add(new ListaSpesa(result.getInt(1), result.getInt(2),
						result.getString(3), result.getBoolean(4), result
								.getString(5), result.getString(6)));
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Quota> getStatisticaUtente(int idUtente) {
		Statement statement;
		List<Quota> lista = new LinkedList<Quota>();
		try {
			statement = connection.createStatement();
			String query = "select * from statistiche where idUtente='"
					+ idUtente + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lista.add(new Quota(result.getInt(1), result.getInt(2), result
						.getInt(3), result.getString(4), result.getString(4)));
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Quota> getStatisticaGruppoSpesa(int idGruppoSpesa) {
		Statement statement;
		List<Quota> lista = new LinkedList<Quota>();
		try {
			statement = connection.createStatement();
			String query = "select * from statistiche where idGruppoSpesa='"
					+ idGruppoSpesa + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lista.add(new Quota(result.getInt(1), result.getInt(2), result
						.getInt(3), result.getString(4), result.getString(4)));
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Quota> getStatisticaUtenteGruppoSpesa(int idUtente,
			int idGruppoSpesa) {
		Statement statement;
		List<Quota> lista = new LinkedList<Quota>();
		try {
			statement = connection.createStatement();
			String query = "select * from statistiche where idUtente='"
					+ idUtente + "', idGruppoSpesa='" + idGruppoSpesa + "'";
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				lista.add(new Quota(result.getInt(1), result.getInt(2), result
						.getInt(3), result.getString(4), result.getString(4)));
			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getTotale(List<Quota> lista) {
		double totale = 0;
		for (Quota quota : lista) {
			totale += Double.parseDouble(quota.getImporto());
		}
		return String.format("%.2f", totale);
	}

	public Quota addStatistica(int idScontrino, int idUtente,
			int idGruppoSpesa, String dataEmissione, String importo) {
		Statement statement;
		Quota nuovaQuota;
		try {
			statement = connection.createStatement();
			String query = "insert into statistiche(idScontrino, idUtente, idGruppoSpesa, dataEmissione, importo) values('"
					+ idScontrino
					+ "','"
					+ idUtente
					+ "','"
					+ idGruppoSpesa
					+ "','" + dataEmissione + "','" + importo + "')";
			statement.executeUpdate(query);
			return new Quota(idScontrino, idUtente, idGruppoSpesa,
					dataEmissione, importo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
