package be.pxl.services.whishlist.services;

import be.pxl.services.whishlist.domain.WhishList;
import be.pxl.services.whishlist.domain.dto.ItemDto;

import java.util.List;

public interface IWishlistService {
    public List<WhishList> getAllWhishlist();

    void AddItem(long userId, ItemDto itemDto);
}
