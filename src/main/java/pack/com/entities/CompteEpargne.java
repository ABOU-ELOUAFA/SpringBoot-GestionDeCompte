package pack.com.entities;

import java.util.Date;

public class CompteEpargne extends Compte {

	private Double taux;

	public CompteEpargne(String numCompte, Date dateCreation, Double solde, Client client, Double taux) {
		super(numCompte, dateCreation, solde, client);
		this.taux = taux;
	}

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

}
