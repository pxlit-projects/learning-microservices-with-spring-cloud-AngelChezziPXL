package be.pxl.services.logbook.controller;

import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import be.pxl.services.logbook.exception.AuthorizationException;
import be.pxl.services.logbook.service.ILogbookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/logbook", headers = "ROLE")
@RequiredArgsConstructor
public class LogbookController {
    private Logger LOG = LoggerFactory.getLogger(LogbookController.class);
    private final ILogbookService logbookService;



     // RETRIEVE ALL LOGBOOK ENTRIES
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductLogResponse> getAll(@RequestHeader Map<String, String> headers) {
        checkAuthorization(headers);
        LOG.debug("Controller method getAll() invoked");
        LOG.info("Fetching all logbook entries.");
        return logbookService.getAll();
    }

    // RETRIEVE ALL LOGBOOK ENTRIES FOR SPECIFIC PRODUCTID
    @GetMapping(params = "product_id")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductLogResponse> getAllById(@RequestHeader Map<String, String> headers, @RequestParam long productid) {
        checkAuthorization(headers);
        LOG.debug("Controller method getAllById() invoked");
        LOG.info("Fetching all logbook entries for id {}.", productid);
        return logbookService.getAllByProductId(productid);
    }

    private void checkAuthorization(Map<String, String> headers) {
        String role = headers.get("ROLE");
        if (!role.equalsIgnoreCase("admin")) {
            LOG.debug("You are not authorized to access the logbook");
            throw new AuthorizationException("You are not allowed to access this resource.");
        }
    }
}
