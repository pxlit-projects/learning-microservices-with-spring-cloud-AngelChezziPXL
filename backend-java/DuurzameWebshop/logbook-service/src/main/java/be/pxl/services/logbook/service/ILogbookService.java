package be.pxl.services.logbook.service;

import be.pxl.services.logbook.domain.dto.ProductLogRequest;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;

import java.util.List;

public interface ILogbookService {

    List<ProductLogResponse> getAll();
    ProductLogResponse addProductLog(ProductLogRequest productLogRequestRequest);

    List<ProductLogResponse> getAllByProductId(long productid);
}

