package it.unical.ingsw.splitMyExpense.domain;

public class ListaSpesa {
	
	private int id;
	private int idGruppo;
	private String dataCreazione;
	private boolean aperta;
	private String dataChiusura;
	private String descrizione;
	
	public ListaSpesa(int id, int idGruppo, String dataCreazione) {
		this.id = id;
		this.idGruppo = idGruppo;
		this.dataCreazione = dataCreazione;
		aperta=true;
		dataChiusura="lista aperta";
	}

	public ListaSpesa(int id, int idGruppo, String dataCreazione,
			boolean aperta, String dataChiusura, String descrizione) {
		super();
		this.id = id;
		this.idGruppo = idGruppo;
		this.dataCreazione = dataCreazione;
		this.aperta = aperta;
		this.dataChiusura = dataChiusura;
		this.descrizione = descrizione;
	}

	public int getId() {
		return id;
	}

	public int getIdGruppo() {
		return idGruppo;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public boolean isAperta() {
		return aperta;
	}

	public void setAperta(boolean aperta) {
		this.aperta = aperta;
	}

	public String getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(String dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaSpesa other = (ListaSpesa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListaSpesa [id=" + id + ", idGruppo=" + idGruppo
				+ ", dataCreazione=" + dataCreazione + ", aperta=" + aperta
				+ ", dataChiusura=" + dataChiusura + ", descrizione="
				+ descrizione + "]";
	}

}
