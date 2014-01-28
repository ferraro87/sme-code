package it.unical.ingsw.splitMyExpense.domain;

public class Scontrino {
	
	private int id;
	private String dataEmissione;
	private int idListaSpesa;
	private int idGruppoSpesa;
	private int idUtentePagante;
	private boolean aperto;
	private String dataChiusura;
	
	public Scontrino(int id, String dataEmissione, int idGruppoSpesa,
			int idUtentePagante) {
		this.id = id;
		this.dataEmissione = dataEmissione;
		this.idGruppoSpesa = idGruppoSpesa;
		this.idUtentePagante = idUtentePagante;
	}

	public Scontrino(int id, String dataEmissione, int idListaSpesa,
			int idGruppoSpesa, int idUtentePagante, boolean aperto,
			String dataChiusura) {
		this.id = id;
		this.dataEmissione = dataEmissione;
		this.idListaSpesa = idListaSpesa;
		this.idGruppoSpesa = idGruppoSpesa;
		this.idUtentePagante = idUtentePagante;
		this.aperto = aperto;
		this.dataChiusura = dataChiusura;
	}

	public int getId() {
		return id;
	}

	public String getDataEmissione() {
		return dataEmissione;
	}

	public int getIdListaSpesa() {
		return idListaSpesa;
	}

	public void setIdListaSpesa(int idListaSpesa) {
		this.idListaSpesa = idListaSpesa;
	}

	public int getIdGruppoSpesa() {
		return idGruppoSpesa;
	}

	public int getIdUtentePagante() {
		return idUtentePagante;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public String getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(String dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	@Override
	public String toString() {
		return "Scontrino [id=" + id + ", dataEmissione=" + dataEmissione
				+ ", idListaSpesa=" + idListaSpesa + ", idGruppoSpesa="
				+ idGruppoSpesa + ", idUtentePagante=" + idUtentePagante
				+ ", aperto=" + aperto + ", dataChiusura=" + dataChiusura + "]";
	}

}
