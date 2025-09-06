package be.pxl.services.productcatalog.service;

import be.pxl.services.productcatalog.domain.dto.ProductQueueMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRabbitMqService {
    void sendMessageToQueue(ProductQueueMessage productQueueMessage) throws JsonProcessingException;
}
