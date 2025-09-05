package be.pxl.services.whishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.pxl.services.whishlist.domain.Whishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Whishlist, Long> {
}
