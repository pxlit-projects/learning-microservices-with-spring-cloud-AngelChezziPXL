package be.pxl.services.logbook.controller;


import be.pxl.services.logbook.builder.ProductLogBuilder;
import be.pxl.services.logbook.domain.ProductLog;
import be.pxl.services.logbook.domain.dto.ProductLogResponse;
import be.pxl.services.logbook.service.ILogbookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class LogbookControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ProductLogBuilder productLogBuilder = new ProductLogBuilder();

    @MockBean
    private ILogbookService logbookServiceMock;

    @Test
    public void getLogAll_ShouldReturnAllLogs() throws Exception {
        //ARRANGE
        List<ProductLogResponse> productLogResponses = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ProductLog productLog = productLogBuilder.withId((long)i).build();
            productLogResponses.add(productLog.toProductLogResponse());
        }

        Mockito.when(logbookServiceMock.getAll()).thenReturn(productLogResponses);

        //ACT & ASSERT
        mockMvc.perform(get("/api/logbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));

        Mockito.verify(logbookServiceMock, Mockito.times(1)).getAll();
    }




}
