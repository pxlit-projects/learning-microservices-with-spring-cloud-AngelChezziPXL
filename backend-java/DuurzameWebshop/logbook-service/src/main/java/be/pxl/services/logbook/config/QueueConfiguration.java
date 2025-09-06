package be.pxl.services.logbook.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {
    private final String QUEUE_NAME = "product-queue";


    @Bean
      public Queue myQueue() {
        return new Queue(QUEUE_NAME, true);
    }
    
}
