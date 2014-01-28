package it.unical.ingsw.splitMyExpense.domain;

public class Quota {
	
	private int idScontrino;
	private int idUtente;
	private int idGruppoSpesa;
	private String dataEmissione;
	private String importo;
	
	public Quota(int idScontrino, int idUtente, int idGruppoSpesa,
			String dataEmissione, String importo) {
		this.idScontrino = idScontrino;
		this.idUtente = idUtente;
		this.idGruppoSpesa = idGruppoSpesa;
		this.dataEmissione = dataEmissione;
		this.importo = importo;
	}

	public int getIdScontrino() {
		return idScontrino;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public int getIdGruppoSpesa() {
		return idGruppoSpesa;
	}

	public String getDataEmissione() {
		return dataEmissione;
	}

	public String getImporto() {
		return importo;
	}

	@Override
	public String toString() {
		return "Quota [idScontrino=" + idScontrino + ", idUtente=" + idUtente
				+ ", idGruppoSpesa=" + idGruppoSpesa + ", dataEmissione="
				+ dataEmissione + ", importo=" + importo + "]";
	}
	
}
