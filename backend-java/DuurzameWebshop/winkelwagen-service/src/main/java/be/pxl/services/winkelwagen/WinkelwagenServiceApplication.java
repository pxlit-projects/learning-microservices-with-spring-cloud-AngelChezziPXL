package be.pxl.services.winkelwagen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WinkelwagenServiceApplication.
 *
 */
@SpringBootApplication
//TODO: @EnableDiscoveryClient toevoegen
//TODO: @EnableFeignClient toevoegen
public class WinkelwagenServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(WinkelwagenServiceApplication.class, args);
    }
}
