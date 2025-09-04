package be.pxl.services.winkelwagen.service;


import be.pxl.services.winkelwagen.service.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto addNewItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto);
    ItemDto getItemById(long id);
    List<ItemDto> getItems();
    void deleteItemById(long id);
}
