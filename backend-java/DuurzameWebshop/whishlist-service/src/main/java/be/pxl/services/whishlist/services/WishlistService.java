package be.pxl.services.whishlist.services;

import be.pxl.services.whishlist.domain.Item;
import be.pxl.services.whishlist.domain.WhishList;
import be.pxl.services.whishlist.domain.dto.ItemDto;
import be.pxl.services.whishlist.repository.IWishlistRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService implements IWishlistService {

    private final IWishlistRepository wishlistRepositoy;

    @Override
    public List<WhishList> getAllWhishlist() {
        throw new NotImplementedException();    }

    @Override
    public void AddItem(long userId, ItemDto itemDto) {
        Item item  = Item.builder()
                .id(itemDto.getId())
                .productId(itemDto.getProductId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .shoppingCartId(itemDto.getShoppingCartId())
                .price(itemDto.getPrice())
                .quantity(itemDto.getQuantity())
                .build();
        WhishList whishlist = getOrCreateUserWhishlist(userId);
        whishlist.addItem(item);
        wishlistRepositoy.save(whishlist);
    }

    private WhishList getOrCreateUserWhishlist(long userId) {
        WhishList whishlist = wishlistRepositoy.findByUserId(userId).orElse(null);
        if (whishlist == null) {
            whishlist = WhishList.builder()
                    .userId(userId)
                    .items(new ArrayList<>())
                    .build();
        }
        return whishlist;
    }
}
