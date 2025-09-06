package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements IRabbitMqService{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate = new RabbitTemplate();
    private final String ROUTE_KEY = "product_queue";

    public void sendMessageToQueue(ProductQueueMessage productQueueMessage) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(productQueueMessage);
        rabbitTemplate.convertAndSend(ROUTE_KEY, productQueueMessage);
    }
}
