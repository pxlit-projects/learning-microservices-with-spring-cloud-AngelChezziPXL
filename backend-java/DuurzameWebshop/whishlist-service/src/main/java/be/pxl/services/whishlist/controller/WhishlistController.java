package be.pxl.services.whishlist.controller;

import be.pxl.services.whishlist.domain.dto.ItemDto;
import be.pxl.services.whishlist.exception.AuthorizationException;
import be.pxl.services.whishlist.services.IWishlistService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/whishlist")
public class WhishlistController {

    private final IWishlistService wishlistService;
    private final Logger LOG = LoggerFactory.getLogger(WhishlistController.class);

    @GetMapping("test")
    @ResponseStatus(HttpStatus.OK)
    public String getWhishlistTest() {
        return "whishlist service is running.";
    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void addToWhishlist(long userId, @RequestBody ItemDto itemDto) {
       wishlistService.AddItem(userId, itemDto);
   }






    //PRIVATE HELPER METHODS
    private void checkAuthorization(Map<String, String> headers) {
        LOG.info("Checking authorization.");
        String role = headers.get("role") != null ? headers.get("role") : null;
        long userId = headers.get("user_id") != null ? Long.parseLong(headers.get("user_id")): 0;
//        if (!role.equalsIgnoreCase("admin")) {
//            LOG.debug("You are not authorized to access the logbook");
//            throw new AuthorizationException("You are not allowed to access this resource.");
//        }

        if(userId < 1) {
            LOG.debug("User id cannot be null");
            throw new AuthorizationException("User id cannot be null or zero");
        }
        LOG.info("Authorization successful.");
    }
}
