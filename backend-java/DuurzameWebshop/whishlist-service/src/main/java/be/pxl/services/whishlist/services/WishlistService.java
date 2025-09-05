package be.pxl.services.whishlist.services;

import be.pxl.services.whishlist.domain.Whishlist;
import be.pxl.services.whishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService implements IWishlistService {

    private final WishlistRepository wishlistRepositoy;

    @Override
    public List<Whishlist> getAllWhishlist() {
        return List.of();
    }
}
