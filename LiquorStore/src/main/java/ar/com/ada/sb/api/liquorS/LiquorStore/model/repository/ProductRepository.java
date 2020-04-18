package ar.com.ada.sb.api.liquorS.LiquorStore.model.repository;

import ar.com.ada.sb.api.liquorS.LiquorStore.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameAndBrand(String name, String brand);
}
