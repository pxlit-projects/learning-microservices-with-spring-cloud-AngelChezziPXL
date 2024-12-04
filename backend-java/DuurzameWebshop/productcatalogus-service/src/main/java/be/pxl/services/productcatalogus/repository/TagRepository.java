package be.pxl.services.productcatalogus.repository;

import be.pxl.services.productcatalogus.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByText(String text);
}
