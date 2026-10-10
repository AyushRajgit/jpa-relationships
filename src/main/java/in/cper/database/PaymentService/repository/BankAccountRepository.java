package in.cper.database.PaymentService.repository;

import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountDetailEntity, Long> {

}