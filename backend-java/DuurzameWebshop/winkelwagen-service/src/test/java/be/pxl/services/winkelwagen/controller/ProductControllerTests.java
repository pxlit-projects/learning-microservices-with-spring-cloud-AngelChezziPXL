package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.service.builder.ProductMockBuilder;
import be.pxl.services.winkelwagen.controller.dto.ProductDto;
import be.pxl.services.winkelwagen.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTests {
    private final ProductMockBuilder productMockBuilder = new ProductMockBuilder();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductService productServiceMock;

    @Test
    public void getAll_ReturnsOkAndListOfProduct() throws Exception {
        //ARRANGE
        List<ProductDto> productDtoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productDtoList.add(ProductDto.fromProduct(productMockBuilder.build()));
        }

        Mockito.when(productServiceMock.getAll()).thenReturn(productDtoList);

        //ACT & ASSERT
        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5));

        Mockito.verify(productServiceMock, Mockito.times(1)).getAll();
    }




}
