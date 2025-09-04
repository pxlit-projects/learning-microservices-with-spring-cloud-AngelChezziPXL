package be.pxl.services.logbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LogbookServiceApplication.
 *
 */
@SpringBootApplication
//TODO: @EnableDiscoveryClient toevoegen
//TODO: @EnableFeignClient toevoegen
public class LogbookServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(LogbookServiceApplication.class, args);
    }
}
