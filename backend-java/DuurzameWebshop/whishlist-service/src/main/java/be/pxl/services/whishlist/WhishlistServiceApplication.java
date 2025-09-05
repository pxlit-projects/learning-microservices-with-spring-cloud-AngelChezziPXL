package be.pxl.services.whishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * WhishlistServiceApplication
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
//TODO: @EnableFeignClient toevoegen
public class WhishlistServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(WhishlistServiceApplication.class, args);
    }
}
