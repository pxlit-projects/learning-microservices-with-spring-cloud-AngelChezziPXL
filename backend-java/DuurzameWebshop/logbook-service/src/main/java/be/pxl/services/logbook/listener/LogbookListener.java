package be.pxl.services.logbook.listener;

import be.pxl.services.logbook.domain.dto.LogbookRequest;
import be.pxl.services.logbook.service.LogbookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogbookListener {

    @Autowired
    private LogbookService logbookService;

    @RabbitListener(queues = "productcatalog-queue")
    public void handleQueue(LogbookRequest logbookRequest) {
        logbookService.addLog(logbookRequest);
    }
}
