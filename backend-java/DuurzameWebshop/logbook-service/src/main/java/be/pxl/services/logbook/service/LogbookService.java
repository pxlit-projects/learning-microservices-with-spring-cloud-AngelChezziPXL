package be.pxl.services.logbook.service;

import be.pxl.services.logbook.domain.ProductLog;
import be.pxl.services.logbook.domain.dto.ProductLogRequest;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import be.pxl.services.logbook.exception.ResourceNotFoundException;
import be.pxl.services.logbook.repository.ILogbookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogbookService implements ILogbookService {
    private final Logger logger = LoggerFactory.getLogger(LogbookService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ILogbookRepository productlogRepository;

    @Override
    public List<ProductLogResponse> getAll() {
        logger.debug("LogbookService.getAll() invoked.");
        return productlogRepository.findAll().stream().map(ProductLog::toProductLogResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductLogResponse> getAllByProductId(long productid) {
        logger.debug("LogbookService.getAllProductId({}) invoked.", productid);
        logger.info("Getting all products by productid: {} ...", productid);
        List<ProductLog> productLogs = productlogRepository.findAllByProductId(productid).get();
        List<ProductLogResponse> result = productLogs.stream().map(ProductLog::toProductLogResponse).collect(Collectors.toList());
        logger.debug("Retrieved productlogs with productId '{}' : {}", productid, result.stream().toString());
        return result;
    }

    @Override
    public ProductLogResponse addProductLog(ProductLogRequest productLogRequest) {
        logger.debug("LogbookService.addLog() method invoked.");
        logger.info("Saving productLog to db....");
        ProductLog productLog = productlogRepository.save(productLogRequest.toProductLog());
        logger.debug("ProductLog saved to db: {}", productLog);
        return productLog.toProductLogResponse();
    }


}
