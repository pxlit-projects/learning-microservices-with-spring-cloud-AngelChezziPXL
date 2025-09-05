package be.pxl.services.logbook.service;

import be.pxl.services.logbook.domain.dto.LogbookRequest;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;

import java.util.List;

public interface ILogbookService {
    List<ProductLogResponse> getAll();

    void addLog(LogbookRequest logbookRequest);
}

