package be.pxl.services.logboek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LogboekServiceApplication.
 *
 */
@SpringBootApplication
//TODO: @EnableDiscoveryClient toevoegen
//TODO: @EnableFeignClient toevoegen
public class LogboekServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(LogboekServiceApplication.class, args);
    }
}
