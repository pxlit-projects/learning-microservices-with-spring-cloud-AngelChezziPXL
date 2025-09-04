package be.pxl.services.logbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * LogbookServiceApplication.
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LogbookServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(LogbookServiceApplication.class, args);
    }
}
