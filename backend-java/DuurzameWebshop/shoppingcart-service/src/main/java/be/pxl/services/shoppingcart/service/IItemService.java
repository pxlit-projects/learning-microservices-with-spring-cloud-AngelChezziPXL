package be.pxl.services.shoppingcart.service;


import be.pxl.services.shoppingcart.domain.dto.ItemDto;

import java.util.List;

public interface IItemService {
    ItemDto addNewItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto);
    ItemDto getItemById(long id);
    List<ItemDto> getItems();
    void deleteItemById(long id);
}
