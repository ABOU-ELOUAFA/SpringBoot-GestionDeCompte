package pack.com.entities;

import java.io.Serializable;
import java.util.Date;

public abstract class Operation implements Serializable {

	private Long numOperation;
	private Date datOperation;
	private Double montant;
	private Compte compte;

	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operation(Date datOperation, Double montant, Compte compte) {
		super();
		this.datOperation = datOperation;
		this.montant = montant;
		this.compte = compte;
	}

	public Long getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(Long numOperation) {
		this.numOperation = numOperation;
	}

	public Date getDatOperation() {
		return datOperation;
	}

	public void setDatOperation(Date datOperation) {
		this.datOperation = datOperation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
