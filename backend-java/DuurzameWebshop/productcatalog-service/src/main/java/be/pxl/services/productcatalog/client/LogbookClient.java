package be.pxl.services.productcatalog.client;

import be.pxl.services.productcatalog.domain.dto.LogbookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "logbook-service")       // name of the destination service in the application.properties
public interface LogbookClient {
    @PostMapping("/logbook")
    void sendNotification(@RequestBody LogbookRequest logbookRequest);

}
