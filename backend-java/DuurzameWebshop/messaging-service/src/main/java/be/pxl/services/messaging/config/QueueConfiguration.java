package be.pxl.services.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

//    @Bean
//    public Queue myQueue() {
////        return new Queue("myQueue", false);
//        return new Queue("myQueue", true);
//        //return new Queue("productcatalog-queue", false);
//    }
    @Bean
      public Queue productQueue() {
        return new Queue("product-queue", true);
    }

//    @Bean
//    public Queue productQueue() {
//        return new Queue("productcatalog-queue", false);
//    }
}
