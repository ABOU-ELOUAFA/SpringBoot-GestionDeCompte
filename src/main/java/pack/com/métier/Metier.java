package pack.com.métier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pack.com.DAO.CompteRepository;
import pack.com.DAO.OperationRepository;
import pack.com.entities.Compte;
import pack.com.entities.CompteCourant;
import pack.com.entities.Operation;
import pack.com.entities.Retrait;
import pack.com.entities.Versement;

@Service
@Transactional
public class Metier implements IMetier {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(int numcompte) {
		Compte compte = compteRepository.findById(numcompte).orElse(null);
		if (compte == null)
			throw new RuntimeException("Compte introuvable");
		return compte;

	}

	@Override
	public void verser(int numcompte, double montant) {
		Compte cpCompte = consulterCompte(numcompte);
		operationRepository.save(new Versement(new Date(), montant, cpCompte));
		cpCompte.setSolde((cpCompte.getSolde()) + montant);
		compteRepository.save(cpCompte);

	}

	@Override
	public void retirer(int numcompte, double montant) {
		Compte cpCompte = consulterCompte(numcompte);
		if (cpCompte instanceof CompteCourant) {
			if (((CompteCourant) cpCompte).getDecouvert() + cpCompte.getSolde() < montant)
				throw new RuntimeException("Solde Insufissant");
			operationRepository.save(new Retrait(new Date(), montant, cpCompte));
			cpCompte.setSolde((cpCompte.getSolde()) - montant);
			compteRepository.save(cpCompte);

		}
	}

	@Override
	public void virement(int numcompte1, int numcompte2, double montant) {
		retirer(numcompte1, montant);
		verser(numcompte2, montant);

	}

	@Override
	public Page<Operation> consulteerOperatio(int numcompte, int page, int size) {
		return operationRepository.listOperation(numcompte, new QPageRequest(page, size));

	}

}
