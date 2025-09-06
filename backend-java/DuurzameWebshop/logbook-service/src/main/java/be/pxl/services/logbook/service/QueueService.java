package be.pxl.services.logbook.service;

import be.pxl.services.logbook.domain.dto.ProductLogRequest;
import be.pxl.services.logbook.domain.dto.ProductQueueMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class QueueService {
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(QueueService.class);
    private final ILogbookService logbookService;
    private final String QUEUE_NAME = "product-queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void processQueueItem(String jsonString) {
        logger.info("Logbook-service received new message. \n ProductQueueMessageDetails: {}.", jsonString);
        ProductQueueMessage productQueueMessage = mapper.convertValue(jsonString, ProductQueueMessage.class);
        logbookService.addProductLog(productQueueMessage.toProductLogRequest());
    }
}
