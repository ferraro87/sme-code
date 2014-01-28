package it.unical.ingsw.splitMyExpense.domain;

public class GruppoSpesa {
	
	private int id;
	private String nome;
	private String descrizione;
	private String foto;
	
	public GruppoSpesa(int id, String nome, String descrizione, String foto) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.foto = foto;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		GruppoSpesa other = (GruppoSpesa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GruppoSpesa [id=" + id + ", nome=" + nome + ", descrizione="
				+ descrizione + ", foto=" + foto + "]";
	}
	
	
	
}
