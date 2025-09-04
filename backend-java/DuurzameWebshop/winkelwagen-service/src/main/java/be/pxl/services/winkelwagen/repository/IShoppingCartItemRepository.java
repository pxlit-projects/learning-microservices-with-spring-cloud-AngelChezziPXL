package be.pxl.services.winkelwagen.repository;

import be.pxl.services.winkelwagen.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartItemRepository extends JpaRepository<CartItem, Long> {
}
