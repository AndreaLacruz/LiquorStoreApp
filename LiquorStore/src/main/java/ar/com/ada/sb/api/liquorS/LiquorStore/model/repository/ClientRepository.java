package ar.com.ada.sb.api.liquorS.LiquorStore.model.repository;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByDni(Integer dni);
}
