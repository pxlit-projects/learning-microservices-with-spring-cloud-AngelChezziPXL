package be.pxl.services.logbook.controller;

import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import be.pxl.services.logbook.service.ILogbookService;
import be.pxl.services.logbook.service.LogbookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logbook")
@RequiredArgsConstructor
public class LogbookController {
    Logger logger = LoggerFactory.getLogger(LogbookController.class);
    private final ILogbookService logbookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductLogResponse> getAll() {
        logger.info("method getAll() invoked");
        return logbookService.getAll();
    }
}
