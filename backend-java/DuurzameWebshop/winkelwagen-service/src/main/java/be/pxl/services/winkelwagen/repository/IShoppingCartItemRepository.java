package be.pxl.services.winkelwagen.repository;

import be.pxl.services.winkelwagen.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartItemRepository extends JpaRepository<Item, Long> {
}
