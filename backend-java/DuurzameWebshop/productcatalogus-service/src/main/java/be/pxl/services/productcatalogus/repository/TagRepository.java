package be.pxl.services.productcatalogus.repository;

import be.pxl.services.productcatalogus.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
