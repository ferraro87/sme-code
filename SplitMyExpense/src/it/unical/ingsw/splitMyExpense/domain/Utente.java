package it.unical.ingsw.splitMyExpense.domain;

public class Utente {
	
	private int id;
	private String email;
	private String password;
	private String nome;
	private String foto;
	
	public Utente(int id, String email, String password, String nome, String foto) {
		this.id=id;
		this.email=email;
		this.password=password;
		this.nome=nome;
		this.foto=foto;
	}
	
	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Utente other = (Utente) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", email=" + email + ", password="
				+ password + ", nome=" + nome + ", foto=" + foto + "]";
	}
	
}
