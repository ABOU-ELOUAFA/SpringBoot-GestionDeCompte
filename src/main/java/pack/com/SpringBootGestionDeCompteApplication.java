package pack.com;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import pack.com.DAO.ClientRepository;
import pack.com.DAO.CompteRepository;
import pack.com.DAO.OperationRepository;
import pack.com.entities.Client;
import pack.com.entities.Compte;
import pack.com.entities.CompteCourant;
import pack.com.entities.CompteEpargne;
import pack.com.entities.Operation;
import pack.com.entities.Retrait;
import pack.com.entities.Versement;
import pack.com.métier.IMetier;

@SpringBootApplication
public class SpringBootGestionDeCompteApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IMetier iMetier;

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringBootGestionDeCompteApplication.class, args);
		// ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
		// clientRepository.save(new Client("Amine", "aminekhan.14@gmail.com"));

	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("Amine", "aminekhan.14@gmail.com"));
		Client c2 = clientRepository.save(new Client("ahmed", "ahmedkhan.15@gmail.com"));

		Compte cp1 = compteRepository.save(new CompteCourant(new Date(), 9000.0, c1, 6000.0));
		Compte cp2 = compteRepository.save(new CompteEpargne(new Date(), 8000.0, c2, 5.0));

		operationRepository.save(new Versement(new Date(), 2000.0, cp1));
		operationRepository.save(new Versement(new Date(), 300.0, cp1));
		operationRepository.save(new Versement(new Date(), 200.0, cp1));

		Operation op1 = operationRepository.save(new Retrait(new Date(), 4000.0, cp1));

		operationRepository.save(new Versement(new Date(), 22000.0, cp2));
		operationRepository.save(new Versement(new Date(), 2500.0, cp2));
		operationRepository.save(new Versement(new Date(), 400.0, cp2));

		Operation op2 = operationRepository.save(new Retrait(new Date(), 3000.0, cp2));

		iMetier.verser(1, 2000);
		iMetier.retirer(1, 11000);

	}

}
