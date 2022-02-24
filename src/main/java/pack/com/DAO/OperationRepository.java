package pack.com.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pack.com.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	@Query("select o from Operation o where o.compte.numCompte=:x order by o.datOperation desc")
	public Page<Operation> listOperation(@Param("x") int numcompte, Pageable pageable);
}
