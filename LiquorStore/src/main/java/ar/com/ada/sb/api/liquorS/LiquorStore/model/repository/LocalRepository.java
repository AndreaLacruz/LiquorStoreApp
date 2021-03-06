package ar.com.ada.sb.api.liquorS.LiquorStore.model.repository;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    @Override
    Optional<Local> findById(Long aLong);
}
