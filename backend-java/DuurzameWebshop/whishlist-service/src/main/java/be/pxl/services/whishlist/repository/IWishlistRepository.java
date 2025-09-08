package be.pxl.services.whishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.pxl.services.whishlist.domain.WhishList;

import java.util.Optional;

@Repository
public interface IWishlistRepository extends JpaRepository<WhishList, Long> {
    Optional<WhishList> findByUserId(long userId);
}
