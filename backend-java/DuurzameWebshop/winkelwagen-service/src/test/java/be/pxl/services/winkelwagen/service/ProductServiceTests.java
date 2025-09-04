package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.controller.dto.ProductDto;
import be.pxl.services.winkelwagen.domain.Product;
import be.pxl.services.winkelwagen.repository.ProductRepository;
import be.pxl.services.winkelwagen.service.builder.ProductMockBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    ProductMockBuilder productBuilder = new ProductMockBuilder();

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getAll_ShouldReturnListOfProductDtos(){
        //ARRANGE
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        for(int i=0; i < 5; i++){
            Product product = productBuilder.build();
            products.add(product);
            productDtos.add(ProductDto.fromProduct(product));
        }

        Mockito.when(productRepositoryMock.findAll()).thenReturn(products);
        //ACT
        var result = productService.getAll();

        //ASSERT
        Assertions.assertEquals(productDtos.size(), result.size());
        Assertions.assertEquals(productDtos.getFirst().getName(), result.getFirst().getName());
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findAll();
    }

}
