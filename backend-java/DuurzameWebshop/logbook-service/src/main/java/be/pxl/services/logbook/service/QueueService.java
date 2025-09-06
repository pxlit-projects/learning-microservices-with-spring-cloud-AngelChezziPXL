package be.pxl.services.logbook.service;

import be.pxl.services.logbook.domain.dto.LogbookRequest;
import be.pxl.services.logbook.domain.dto.ProductQueueMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(QueueService.class);
    @Autowired
    private ILogbookService logbookService;
    private final String QUEUE_NAME = "product-queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void processQueueItem(String jsonString) {
        logger.info("Logbook-service received new message: {}.", jsonString);
        ProductQueueMessage pqm = mapper.convertValue(jsonString, ProductQueueMessage.class);
        LogbookRequest logbookRequest = LogbookRequest.builder()
                .userId(pqm.getUserId())
                .serviceName(pqm.getServiceName())
                .timestamp(pqm.getTimestamp())
                .productResponse(pqm.getProductResponse())
                .build();
        logbookService.addLog(logbookRequest);
    }
}
