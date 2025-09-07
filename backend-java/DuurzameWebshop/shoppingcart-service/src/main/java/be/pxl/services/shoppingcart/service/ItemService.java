package be.pxl.services.shoppingcart.service;

import be.pxl.services.shoppingcart.domain.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Override
    public ItemDto getItemById(long id) {
        return null;
    }

    @Override
    public List<ItemDto> getItems() {
        return List.of();
    }

    @Override
    public void deleteItemById(long id) {

    }
}
