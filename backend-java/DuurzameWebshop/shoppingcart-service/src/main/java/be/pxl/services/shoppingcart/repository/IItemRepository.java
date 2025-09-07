package be.pxl.services.shoppingcart.repository;

import be.pxl.services.shoppingcart.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
