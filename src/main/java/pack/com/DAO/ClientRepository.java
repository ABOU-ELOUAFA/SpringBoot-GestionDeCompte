package pack.com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.com.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
