package be.pxl.services.logbook.repository;

import be.pxl.services.logbook.domain.ProductLog;
import com.netflix.spectator.api.NoopRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILogbookRepository extends JpaRepository<ProductLog, Long> {
    Optional<List<ProductLog>> findAllByProductId(Long productId);
}
