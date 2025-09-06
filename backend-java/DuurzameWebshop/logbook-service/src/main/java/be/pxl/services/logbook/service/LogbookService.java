package be.pxl.services.logbook.service;

import be.pxl.services.logbook.domain.ProductLog;
import be.pxl.services.logbook.domain.dto.LogbookRequest;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import be.pxl.services.logbook.repository.ILogbookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogbookService implements ILogbookService {
    private final Logger logger = LoggerFactory.getLogger(LogbookService.class);

    @Autowired
    ILogbookRepository productlogRepository;

    @Override
    public List<ProductLogResponse> getAll() {
        logger.info("LogbookService.getAll() invoked.");
        return productlogRepository.findAll().stream().map(ProductLog::toProductLogResponse).collect(Collectors.toList());
    }

    @Override
    public void addLog(LogbookRequest logbookRequest) {
        logger.info("LogbookService.addLog() method invoked.");
        ProductLog productLog = ProductLog.builder()
                        .userId(logbookRequest.getUserId())
                        .timeStamp(logbookRequest.getTimestamp())
                        .productId(logbookRequest.getProductResponse().getId())
                        .name(logbookRequest.getProductResponse().getName()).description(logbookRequest.getProductResponse().getDescription())
                        .categoryName(logbookRequest.getProductResponse().getCategoryName())
                        .available(logbookRequest.getProductResponse().getAvailable())
                        .categoryName(logbookRequest.getProductResponse().getCategoryName())
                        .tags(logbookRequest.getProductResponse().getTags())
                        .price(logbookRequest.getProductResponse().getPrice())
                        .build();
        logger.info("Saving product log to db.");
        productlogRepository.save(productLog);
    }


}
