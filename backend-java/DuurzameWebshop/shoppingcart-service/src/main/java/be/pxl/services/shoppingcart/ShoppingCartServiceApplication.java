package be.pxl.services.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ShoppingCartServiceApplication.
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
//TODO: @EnableFeignClient toevoegen
public class ShoppingCartServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ShoppingCartServiceApplication.class, args);
    }
}
