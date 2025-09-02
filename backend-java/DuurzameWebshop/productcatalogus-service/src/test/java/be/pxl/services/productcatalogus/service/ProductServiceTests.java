package be.pxl.services.productcatalogus.service;


import be.pxl.services.productcatalogus.builders.ProductBuilder;
import be.pxl.services.productcatalogus.domain.Product;
import be.pxl.services.productcatalogus.domain.dto.ProductRequest;
import be.pxl.services.productcatalogus.domain.dto.ProductResponse;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import be.pxl.services.productcatalogus.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    ProductBuilder productBuilder = new ProductBuilder();

    @Mock
    private ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);
    @Mock
    private CategoryRepository categoryRepositoryMock = Mockito.mock(CategoryRepository.class);

    @InjectMocks
    ProductService productService;

    @Test
    public void findAll_ShouldReturnAllProducts_AsListOfProductResponses() throws Exception {
        //ARRANGE
        List<Product> products = new ArrayList<>();
        int numberOfProducts = 5;
        for (int i = 1; i <= numberOfProducts; i++) {
            products.add(productBuilder.withId((long)i).build());
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
        long validId = 1L;
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

    @Test
    public void findByInvalidId_ShouldThrowResponseStatusExceptionWithNotFoundCode() throws Exception {
        //ARRANGE
        long invalidId = 1;
        Mockito.when(productRepositoryMock.findById(invalidId)).thenReturn(Optional.empty());

        //ACT and ASSERT
        var ex = Assertions.assertThrows(ResponseStatusException.class, () -> productService.findById(invalidId));
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(invalidId);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    public void updateProduct_WithValidId_ShouldUpdateProduct() throws Exception {
        //ARRANGE
        long validId = 1L;
        Product product = productBuilder.withId(validId).build();
        Mockito.when(productRepositoryMock.findById(validId)).thenReturn(Optional.of(product));
        ProductRequest productRequest = ProductRequest.builder()
                .name(product.getName())
                .tags(product.getTags())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .description(product.getDescription())
                .build();
        Mockito.when(productRepositoryMock.save(Mockito.any(Product.class))).thenReturn(product);

        // ACT
        productService.updateProduct(validId, productRequest);

        //ASSERT
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(validId);
        Mockito.verify(productRepositoryMock, Mockito.times(1)).save(Mockito.any(Product.class));




    }


}
