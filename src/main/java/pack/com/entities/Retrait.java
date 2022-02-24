package pack.com.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date datOperation, Double montant, Compte compte) {
		super(datOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

}
