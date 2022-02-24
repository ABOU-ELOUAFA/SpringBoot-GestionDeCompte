package pack.com.métier;

import org.springframework.data.domain.Page;

import pack.com.entities.Compte;
import pack.com.entities.Operation;

public interface IMetier {
	public Compte consulterCompte(int numcompte);

	void verser(int numcompte, double montant);

	void retirer(int numcompte, double montant);

	void virement(int numcompte1, int numcompte2, double montant);

	Page<Operation> consulteerOperatio(int numcompte, int page, int size);

}
