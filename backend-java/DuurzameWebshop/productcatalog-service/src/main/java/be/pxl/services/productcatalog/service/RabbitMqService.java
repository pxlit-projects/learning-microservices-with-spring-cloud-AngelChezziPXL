package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements IRabbitMqService{
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private RabbitTemplate rabbitTemplate = new RabbitTemplate();
    private final String ROUTE_KEY = "product-queue";

    public void sendMessageToQueue(ProductQueueMessage productQueueMessage) throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(productQueueMessage);
        rabbitTemplate.convertAndSend(ROUTE_KEY, jsonString);
    }
}
