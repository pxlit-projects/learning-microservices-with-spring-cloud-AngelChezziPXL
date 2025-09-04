package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.service.builder.ProductMockBuilder;
import be.pxl.services.winkelwagen.controller.dto.ProductDto;
import be.pxl.services.winkelwagen.service.ProductService;
import be.pxl.services.winkelwagen.service.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void update_ProductWithValidId_ShouldUpdateAndReturnOk() throws Exception {
        //ARRANGE
        Long validId = 1L;
        ProductDto productDto = ProductDto.fromProduct(productMockBuilder.withId(validId).build());
        Mockito.when(productServiceMock.updateProduct(Mockito.any(ProductDto.class))).thenReturn(productDto);

        //ACT & ASSERT
        mockMvc.perform(put("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDto.getId()));

        Mockito.verify(productServiceMock, Mockito.times(1)).updateProduct(Mockito.any(ProductDto.class));
    }

    @Test
    public void update_ProductWithInvalidId_ShouldReturnNotFound() throws Exception {
        //ARRANGE
        Long invalidId = 1L;
        ProductDto productDto = ProductDto.fromProduct(productMockBuilder.withId(invalidId).build());
        Mockito.when(productServiceMock.updateProduct(Mockito.any(ProductDto.class))).thenThrow(new ResourceNotFoundException("id " + invalidId + " not found"));

        //ACT & ASSERT
        mockMvc.perform(put("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isNotFound());

        Mockito.verify(productServiceMock, Mockito.times(1)).updateProduct(Mockito.any(ProductDto.class));
    }

    @Test
    public void delete_ValidId_ShouldDeleteAndReturnNoContent() throws Exception {
        //ARRANGE
        Long validId = 1L;
        Mockito.doNothing().when(productServiceMock).deleteProduct(Mockito.any(Long.class));

        //ACT & ASSERT
        mockMvc.perform(delete("/api/product/{id}", validId))
                .andExpect(status().isNoContent());

        Mockito.verify(productServiceMock, Mockito.times(1)).deleteProduct(Mockito.anyLong());
    }

    @Test
    public void delete_InvalidId_ShouldReturnNotFound() throws Exception {
        //ARRANGE
        Long invalidId = 1L;
        Mockito.doThrow(new ResourceNotFoundException("id " + invalidId + " not found")).when(productServiceMock).deleteProduct(Mockito.anyLong());

        //ACT & ASSERT
        mockMvc.perform(delete("/api/product/{id}", invalidId))
                .andExpect(status().isNotFound());

        Mockito.verify(productServiceMock, Mockito.times(1)).deleteProduct(Mockito.anyLong());
    }





}
