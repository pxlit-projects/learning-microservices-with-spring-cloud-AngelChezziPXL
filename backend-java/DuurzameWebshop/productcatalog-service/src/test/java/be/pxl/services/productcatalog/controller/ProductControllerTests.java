package be.pxl.services.productcatalog.controller;

import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import be.pxl.services.productcatalog.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTests {
    public final String ROLE_HEADER_VALUE = "admin";
    public final String USER_ID_HEADER_VALUE = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllProducts_shouldReturnListOfProducts() throws Exception {
        List<ProductResponse> products = Arrays.asList(
                new ProductResponse(1L, "Product1", "Description1", "category1", true, List.of("tag1", "tag2"), 100.0),
                new ProductResponse(2L, "Product2", "Description2", "category2", true, List.of("tag1", "tag2"), 100.0)
        );

       Mockito.when(productServiceMock.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[1].name").value("Product2"));
    }


    @Test
    public void getProductById_shouldReturnProduct() throws Exception {
        ProductResponse product = new ProductResponse(1L, "Product1", "Description1", "category1", true, List.of("tag1", "tag2"), 100.0);

        Mockito.when(productServiceMock.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.description").value("Description1"));
    }

    @Test
    public void getProductById_InvalidId_shouldReturnNotFound() throws Exception {
        long invalidId = 1L;

        Mockito.when(productServiceMock.findById(invalidId))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id %d not found", invalidId)));

        mockMvc.perform(get("/api/product/{id}", invalidId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createProduct_shouldReturnCreatedStatus() throws Exception {
        ProductRequest productRequest = new ProductRequest("Product1", "Description1", "category1", true, List.of("tag1", "tag2"), 100.0);

        mockMvc.perform(post("/api/product")
                        .header("role", ROLE_HEADER_VALUE)
                        .header("user_id", USER_ID_HEADER_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isCreated());

        Mockito.verify(productServiceMock, Mockito.times(1)).addProduct(Mockito.anyLong(), any(ProductRequest.class));
    }

    @Test
    public void updateProduct_shouldReturnOkStatus() throws Exception {
        ProductRequest productRequest = new ProductRequest("Product1", "Description1", "category2", true, List.of("tag1", "tag2","newTag"), 150.0);

        mockMvc.perform(put("/api/product/1")
                        .header("role", ROLE_HEADER_VALUE)
                        .header("user_id", USER_ID_HEADER_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk());

        Mockito.verify(productServiceMock).updateProduct(Mockito.anyLong(), eq(1L), any(ProductRequest.class));
    }

    @Test
    public void updateProduct_InvalidId_ShouldReturnNotFound() throws Exception {
        long invalidId = 1L;
        ProductRequest productRequest = new ProductRequest("Product1", "Description1", "category2", true, List.of("tag1", "tag2","newTag"), 150.0);

        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"")).when(productServiceMock).updateProduct(Mockito.anyLong(), Mockito.anyLong(), eq(productRequest));


        mockMvc.perform(put("/api/product/" + invalidId)
                        .header("role", ROLE_HEADER_VALUE)
                        .header("user_id", USER_ID_HEADER_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                    .andExpect(status().isNotFound());

        Mockito.verify(productServiceMock).updateProduct(Mockito.anyLong(), eq(invalidId), eq(productRequest));
    }

    @Test
    public void deleteProduct_shouldReturnAcceptedStatus() throws Exception {
        long validId = 1L;
        mockMvc.perform(delete("/api/product/1")
                        .header("role", ROLE_HEADER_VALUE)
                        .header("user_id", USER_ID_HEADER_VALUE))
                .andExpect(status().isAccepted());

        Mockito.verify(productServiceMock).deleteProduct(Mockito.anyLong(), eq(validId));
    }

    @Test
    public void deleteProduct_InvalidId_ShouldReturnNotFound() throws Exception {
        long invalidId = 1L;
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"")).when(productServiceMock).deleteProduct(Mockito.anyLong(), eq(invalidId));


        mockMvc.perform(delete("/api/product/" + invalidId)
                        .header("role", ROLE_HEADER_VALUE)
                        .header("user_id", USER_ID_HEADER_VALUE))
                .andExpect(status().isNotFound());

        Mockito.verify(productServiceMock).deleteProduct(Mockito.anyLong(), eq(invalidId));
    }
}

