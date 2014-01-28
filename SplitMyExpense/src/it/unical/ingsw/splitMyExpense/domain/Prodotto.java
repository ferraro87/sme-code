package it.unical.ingsw.splitMyExpense.domain;

public class Prodotto {
	
	private int id;
	private String nome;
	private String prezzo;
	private int idLista;
	private int idScontrino;
	
	public Prodotto(int id, String nome, int idListaSpesa) {
		this.id = id;
		this.nome = nome;
		this.idLista = idListaSpesa;
	}
	
	public Prodotto(int id, String nome, String prezzo, int idScontrino) {
		this.id = id;
		this.nome = nome;
		this.prezzo=prezzo;
		this.idScontrino = idScontrino;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getId() {
		return id;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public int getIdScontrino() {
		return idScontrino;
	}

	public void setIdScontrino(int idScontrino) {
		this.idScontrino = idScontrino;
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
		Prodotto other = (Prodotto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo
				+ ", idLista=" + idLista + ", idScontrino=" + idScontrino + "]";
	}

}
