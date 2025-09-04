package be.pxl.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.pxl.services.domain.Whishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Whishlist, Long> {
}
