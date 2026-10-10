package in.cper.database.PaymentService.repository;

import in.cper.database.PaymentService.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {

    List<TransactionRecord> findByCustomerId(Long customerId);
}
