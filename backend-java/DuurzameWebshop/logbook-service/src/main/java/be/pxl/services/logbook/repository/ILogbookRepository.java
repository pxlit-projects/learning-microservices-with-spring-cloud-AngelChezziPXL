package be.pxl.services.logbook.repository;

import be.pxl.services.logbook.domain.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILogbookRepository extends JpaRepository<ProductLog, Long> {
}
