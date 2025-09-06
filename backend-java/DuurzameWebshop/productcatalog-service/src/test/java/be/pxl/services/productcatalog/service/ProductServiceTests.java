package be.pxl.services.productcatalog.service;


import be.pxl.services.productcatalog.builders.ProductBuilder;
import be.pxl.services.productcatalog.domain.Product;
import be.pxl.services.productcatalog.domain.dto.ProductRequest;
import be.pxl.services.productcatalog.domain.dto.ProductResponse;
import be.pxl.services.productcatalog.exception.ResourceNotFoundException;
import be.pxl.services.productcatalog.repository.CategoryRepository;
import be.pxl.services.productcatalog.repository.ProductRepository;
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
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;

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
    public void findByInvalidId_ShouldThrowResourceNotFoudException() throws Exception {
        //ARRANGE
        long invalidId = 1;
        Mockito.when(productRepositoryMock.findById(invalidId)).thenReturn(Optional.empty());

        //ACT and ASSERT
        var ex = Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.findById(invalidId));
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(invalidId);
    }

    @Test
    public void addProduct_ShouldSaveProduct_AndReturnProductResponse() throws Exception {
        //ARRANGE
        Product expectedProduct = productBuilder.withId(1L).build();
        ProductRequest productRequest = mapProductToProductRequest(expectedProduct);
        productRequest.setUserId(1L);

        Mockito.when(productRepositoryMock.save(Mockito.any(Product.class))).thenReturn(expectedProduct);
        Mockito.when(categoryRepositoryMock.findByName(Mockito.anyString())).thenReturn(Optional.of(expectedProduct.getCategory()));

        //ACT
        productService.addProduct(productRequest);

        //ASSERT
        Mockito.verify(productRepositoryMock, Mockito.times(1)).save(Mockito.any(Product.class));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(expectedProduct.getCategory().getName());
    }

    @Test
    public void updateProduct_WithValidId_ShouldUpdateProduct() throws Exception {
        //ARRANGE
        long validId = 1L;
        Product product = productBuilder.withId(validId).build();
        ProductRequest productRequest = mapProductToProductRequest(product);
        productRequest.setUserId(1L);
        Mockito.when(productRepositoryMock.findById(validId)).thenReturn(Optional.of(product));
        Mockito.when(categoryRepositoryMock.findByName(product.getCategory().getName())).thenReturn(Optional.of(product.getCategory()));
        Mockito.when(productRepositoryMock.save(Mockito.any(Product.class))).thenReturn(product);

        // ACT
        productService.updateProduct(validId, productRequest);

        //ASSERT
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(validId);
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(product.getCategory().getName());
        Mockito.verify(productRepositoryMock, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    public void updateProduct_InValidId_ShouldThrowResourceNotFoundException() throws Exception {
        //ARRANGE
        long invalidId = 1L;
        Product product = productBuilder.withId(invalidId).build();
        Mockito.when(productRepositoryMock.findById(invalidId)).thenThrow(new ResourceNotFoundException(String.format("Product with id %s not found.", invalidId)));
        ProductRequest productRequest = mapProductToProductRequest(product);

        //ACT & ASSERT
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(invalidId, productRequest));
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(invalidId);
        Mockito.verify(categoryRepositoryMock, Mockito.never()).findByName(Mockito.anyString());
        Mockito.verify(productRepositoryMock, Mockito.never()).save(Mockito.any(Product.class));
    }

    @Test
    public void deleteProduct_ShouldDeleteProduct() throws Exception {
        //Arrange
        long validId = 1L;
        Mockito.when(productRepositoryMock.existsById(validId)).thenReturn(true);
        Mockito.doNothing().when(productRepositoryMock).deleteById(validId);

        //Act
        productService.deleteProduct(validId);
        //Assert
        Mockito.verify(productRepositoryMock, Mockito.times(1)).deleteById(validId);
        Mockito.verify(productRepositoryMock, Mockito.times(1)).existsById(validId);
    }

    @Test
    public void deleteProduct_InValidId_ShouldThrowResourceNotFoundException() throws Exception {
        //ARRANGE
        long invalidId = 1L;
        Mockito.when(productRepositoryMock.existsById(invalidId)).thenThrow(new ResourceNotFoundException(String.format("Product with id %s not found.", invalidId)));

        //ACT & ASSERT
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(invalidId));
        Mockito.verify(productRepositoryMock, Mockito.times(1)).existsById(invalidId);
    }

    // PRIVATE HELPER METHODS
    private ProductRequest mapProductToProductRequest(Product product) {
        ProductRequest productRequest = ProductRequest.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .tags(product.getTags())
                .available(product.isAvailable())
                .build();
        return productRequest;
    }
}
