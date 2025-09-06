package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService implements IRabbitMqService{
    ObjectMapper mapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;
    private final String QUEUE_NAME = "product-queue";

    public void sendMessageToQueue(ProductQueueMessage productQueueMessage) throws JsonProcessingException {
        String jsonString = mapper.writeValueAsString(productQueueMessage);
        rabbitTemplate.convertAndSend(QUEUE_NAME, jsonString);
    }
}