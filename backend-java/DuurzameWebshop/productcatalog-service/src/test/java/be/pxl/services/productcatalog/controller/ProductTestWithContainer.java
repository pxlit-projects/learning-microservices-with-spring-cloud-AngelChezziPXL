//package be.pxl.services.productcatalog.controller;
//
//import be.pxl.services.productcatalog.builders.ProductBuilder;
//import be.pxl.services.productcatalog.domain.dto.CategoryRequest;
//import be.pxl.services.productcatalog.domain.dto.ProductRequest;
//import be.pxl.services.productcatalog.repository.IProductRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.ArrayList;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//public class ProductTestWithContainer {
//    private ProductBuilder productBuilder = new ProductBuilder();
//    private ObjectMapper objectMapper = new ObjectMapper();
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    IProductRepository productRepository;
//
//    @Container
//    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7.37");
//
//    @DynamicPropertySource          // it is to overwrite the properties in the application.properties
//    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", mySQLContainer::getUsername);
//        registry.add("spring.datasource.password", mySQLContainer::getPassword);
//    }
//
//    @Test
//    public void testCreateProduct() throws Exception {
//        CategoryRequest categoryRequest = CategoryRequest.builder().categoryName("pc").build();
//        String categoryRequestString = objectMapper.writeValueAsString(categoryRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/category")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(categoryRequestString))
//                .andExpect(status().isCreated());
//
//        ProductRequest productRequest = ProductRequest.builder()
//                .name("newProduct")
//                .description("description")
//                .tags(new ArrayList<>())
//                .price(1.30)
//                .available(true)
//                .categoryName("laptop")
//                .build();
//        String productRequestString = objectMapper.writeValueAsString(productRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(productRequestString))
//                .andExpect(status().isCreated());
//    }
//}
