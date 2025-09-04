package be.pxl.services.shoppingcart.repository;

import be.pxl.services.shoppingcart.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartItemRepository extends JpaRepository<Item, Long> {
}
