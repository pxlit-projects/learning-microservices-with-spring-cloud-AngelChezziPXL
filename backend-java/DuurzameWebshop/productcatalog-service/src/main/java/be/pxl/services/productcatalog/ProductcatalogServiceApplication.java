package be.pxl.services.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ProductcatalogServiceApplication.
 */
@SpringBootApplication
//TODO: @EnableDiscoveryClient toevoegen
//TODO: @EnableFeignClient toevoegen
public class ProductcatalogServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductcatalogServiceApplication.class, args);
    }
}
