package be.pxl.services.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ProductcatalogServiceApplication.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductcatalogServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductcatalogServiceApplication.class, args);
    }
}
