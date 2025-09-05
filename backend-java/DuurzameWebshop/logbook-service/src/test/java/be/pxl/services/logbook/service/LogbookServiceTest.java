package be.pxl.services.logbook.service;


import be.pxl.services.logbook.builder.ProductLogBuilder;
import be.pxl.services.logbook.domain.ProductLog;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import be.pxl.services.logbook.repository.ILogbookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LogbookServiceTest {
    private ProductLogBuilder productLogBuilder = new ProductLogBuilder();

    @Mock
    private ILogbookRepository logbookRepositoryMock;

    @InjectMocks
    private LogbookService logbookService;

    @Test
    public void getAll_ShouldReturnListOfLogBookResponse() {
        // ARRANGE
        List<ProductLog> productLogs = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ProductLog productLog = productLogBuilder.withId((long)i).build();
            productLogs.add(productLog);
        }
        Mockito.when(logbookRepositoryMock.findAll()).thenReturn(productLogs);

        // ACT

        List<ProductLogResponse> productLogResponses = logbookService.getAll();

        // ASSERT
        Mockito.verify(logbookRepositoryMock, Mockito.times(1)).findAll();
        Assertions.assertEquals(productLogResponses.size(), 10);
    }
}
