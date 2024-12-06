package be.pxl.services.productcatalogus.controller;

import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import be.pxl.services.productcatalogus.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProducts_shouldReturnListOfProducts() throws Exception {
        List<ProductResponse> products = Arrays.asList(
                new ProductResponse(1L, "Product1", "Description1", "category1", true, List.of("tag1", "tag2"), 100.0),
                new ProductResponse(2L, "Product2", "Description2", "category2", true, List.of("tag1", "tag2"), 100.0)
        );

        Mockito.when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[1].name").value("Product2"));
    }

    @Test
    void getProductById_shouldReturnProduct() throws Exception {
        ProductResponse product = new ProductResponse(1L, "Product1", "Description1", "category1", true, List.of("tag1", "tag2"), 100.0);

        Mockito.when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.description").value("Description1"));
    }

    @Test
    void createProduct_shouldReturnCreatedStatus() throws Exception {
        ProductRequest productRequest = new ProductRequest("Product1", "Description1", "category1", true, List.of("tag1", "tag2"), 100.0);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isCreated());

        Mockito.verify(productService).addProduct(any(ProductRequest.class));
    }

    @Test
    void updateProduct_shouldReturnOkStatus() throws Exception {
        ProductRequest productRequest = new ProductRequest("Product1", "Description1", "category2", true, List.of("tag1", "tag2","newTag"), 150.0);

        mockMvc.perform(put("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk());

        Mockito.verify(productService).updateProduct(eq(1L), any(ProductRequest.class));
    }

    @Test
    void deleteProduct_shouldReturnAcceptedStatus() throws Exception {
        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isAccepted());

        Mockito.verify(productService).deleteProduct(1L);
    }
}

