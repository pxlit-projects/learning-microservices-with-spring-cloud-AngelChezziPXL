package be.pxl.services.productcatalogus.service;


import be.pxl.services.productcatalogus.builders.ProductBuilder;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import be.pxl.services.productcatalogus.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    ProductBuilder productBuilder = new ProductBuilder();

    @Mock
    private ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);

    @InjectMocks
    ProductService productService;

    @Test
    public void findAll_ShouldReturnAllProducts_AsListOfProductResponses() throws Exception {
        //ARRANGE
        List<Product> products = new ArrayList<>();
        int numberOfProducts = 5;
        for (int i = 1; i <= numberOfProducts; i++) {
            products.add(productBuilder.withId(i).build());
        }
        Mockito.when(productRepositoryMock.findAll()).thenReturn(products);

        //ACT
        List<ProductResponse> productsResponses = productService.findAll();

        //ASSERT
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findAll();
        Assertions.assertEquals(productsResponses.size(), numberOfProducts);
        Assertions.assertInstanceOf(ProductResponse.class, productsResponses.get(0));
    }

    @Test
    public void findById_ShouldReturnProduct_AsProductResponse() throws Exception {
        //ARRANGE
        long validId = 1;
        Product product = productBuilder.withId(validId).build();
        Mockito.when(productRepositoryMock.findById(validId)).thenReturn(Optional.of(product));

        //ACT
        var productResponse = productService.findById(validId);

        //ASSERT
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(validId);
        Assertions.assertInstanceOf(ProductResponse.class, productResponse);
        Assertions.assertEquals(product.getId(),productResponse.getId());
        Assertions.assertEquals(productResponse.getName(), product.getName());
        Assertions.assertEquals(productResponse.getPrice(), product.getPrice());
        Assertions.assertEquals(productResponse.getDescription(), product.getDescription());
        Assertions.assertEquals(product.getCategory().getName(), productResponse.getCategoryName());
    }
}
