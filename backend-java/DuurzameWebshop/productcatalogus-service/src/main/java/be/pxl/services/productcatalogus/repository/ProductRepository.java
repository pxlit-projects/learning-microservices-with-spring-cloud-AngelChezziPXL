package be.pxl.services.productcatalogus.repository;

import be.pxl.services.productcatalogus.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
