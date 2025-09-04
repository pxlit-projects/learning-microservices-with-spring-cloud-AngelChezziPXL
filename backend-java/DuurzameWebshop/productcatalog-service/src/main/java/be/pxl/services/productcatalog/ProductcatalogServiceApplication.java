package be.pxl.services.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ProductcatalogServiceApplication.
 */
@SpringBootApplication
@EnableDiscoveryClient
//TODO: add @EnableFeignClient
public class ProductcatalogServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductcatalogServiceApplication.class, args);
    }
}
