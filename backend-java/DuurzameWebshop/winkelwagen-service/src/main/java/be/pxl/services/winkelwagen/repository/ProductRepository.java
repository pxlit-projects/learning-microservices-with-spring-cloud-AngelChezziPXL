package be.pxl.services.winkelwagen.repository;

import be.pxl.services.winkelwagen.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
