package be.pxl.services.productcatalogus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ProductcatalogusServiceApplication.
 */
@SpringBootApplication
//TODO: @EnableDiscoveryClient toevoegen
//TODO: @EnableFeignClient toevoegen
public class ProductcatalogusServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductcatalogusServiceApplication.class, args);
    }
}
