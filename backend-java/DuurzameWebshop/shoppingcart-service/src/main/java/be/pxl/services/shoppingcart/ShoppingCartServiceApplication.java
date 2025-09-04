package be.pxl.services.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ShoppingCartServiceApplication.
 *
 */
@SpringBootApplication
//TODO: @EnableDiscoveryClient toevoegen
//TODO: @EnableFeignClient toevoegen
public class ShoppingCartServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ShoppingCartServiceApplication.class, args);
    }
}
