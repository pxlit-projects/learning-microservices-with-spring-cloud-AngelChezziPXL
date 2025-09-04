package be.pxl.services.shoppingcart.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = SchoppingCartControllerTest.class)
public class SchoppingCartControllerTest {

    @Test
    public void addItemToShoppingCart_WithoutId_ShouldCreateNewSchoppingCart_AndAddNewItemToShoppingCart() throws Exception {

    }
}
