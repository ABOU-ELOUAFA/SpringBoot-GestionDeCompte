package pack.com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.com.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}
