package pack.com.entities;

import java.util.Date;

public class CompteCourant extends Compte {

	private Double decouvert;

	public CompteCourant(String numCompte, Date dateCreation, Double solde, Client client, Double decouvert) {
		super(numCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(Double decouvert) {
		this.decouvert = decouvert;
	}

}
