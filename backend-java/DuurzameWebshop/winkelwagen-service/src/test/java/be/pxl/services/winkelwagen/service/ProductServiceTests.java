package be.pxl.services.winkelwagen.service;

import be.pxl.services.winkelwagen.controller.dto.ProductDto;
import be.pxl.services.winkelwagen.domain.Product;
import be.pxl.services.winkelwagen.repository.ProductRepository;
import be.pxl.services.winkelwagen.service.builder.ProductMockBuilder;
import be.pxl.services.winkelwagen.service.exception.ResourceNotFoundException;
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

import static org.mockito.Mockito.never;

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

    @Test void addProduct_ShouldAddProduct(){
        //ARRANGE
        Product product = productBuilder.build();
        ProductDto productDto = ProductDto.fromProduct(product);
        productDto.setId(null);
        Mockito.when(productRepositoryMock.save(Mockito.any(Product.class))).thenReturn(product);

        //ACT
        var result = productService.addProduct(productDto);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(product.getId(), result.getId());
        Mockito.verify(productRepositoryMock, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test void updateProduct_ValidId_ShouldUpdateProduct(){
        //ARRANGE
        Product product = productBuilder.build();
        ProductDto productDto = ProductDto.fromProduct(product);
        Mockito.when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Mockito.when(productRepositoryMock.save(Mockito.any(Product.class))).thenReturn(product);

        //ACT
        var result = productService.updateProduct(productDto);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(product.getId(), result.getId());
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(productDto.getId());
        Mockito.verify(productRepositoryMock, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test void updateProduct_invalidId_ShouldThrowResourceNotFoundException(){
        //ARRANGE
        Long invaliedId = 1L;
        Product product = productBuilder.withId(invaliedId).build();
        ProductDto productDto = ProductDto.fromProduct(product);
        Mockito.doThrow(new ResourceNotFoundException("Id " + invaliedId + " not found.")).when(productRepositoryMock).findById(Mockito.anyLong());

        //ACT & ASSERT
        var ex = Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(productDto));
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(productDto.getId());
        Mockito.verify(productRepositoryMock, never()).save(Mockito.any(Product.class));
        Assertions.assertTrue(ex.getMessage().contains(invaliedId + " not found"), "Exception message must contain the words '<id> not found");
    }

    @Test void deleteProduct_ValidId_ShouldDeleteProduct(){
        //ARRANGE
        Long validId = 1L;
        Product product = productBuilder.withId(validId).build();
        Mockito.when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepositoryMock).deleteById(validId);

        //ACT
        productService.deleteProduct(validId);

        //ASSERT
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(validId);
        Mockito.verify(productRepositoryMock, Mockito.times(1)).deleteById(validId);
    }

    @Test void deleteProduct_invalidId_ShouldThrowResourceNotFoundException(){
        Long invaliedId = 1L;
        Mockito.doThrow(new ResourceNotFoundException("Id " + invaliedId + " not found.")).when(productRepositoryMock).findById(Mockito.anyLong());

        //ACT & ASSERT
        var ex = Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(invaliedId));
        Assertions.assertTrue(ex.getMessage().contains(invaliedId + " not found"), "Exception message must contain the words '<id> not found");
        Mockito.verify(productRepositoryMock, Mockito.times(1)).findById(invaliedId);
        Mockito.verify(productRepositoryMock, never()).deleteById(invaliedId);

    }


}
