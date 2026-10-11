package in.cper.database.PaymentService.repository;

import in.cper.database.PaymentService.entity.BankAccountDetailEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountDetailEntity, Long> {

    Optional<BankAccountDetailEntity> findByBankAccountNumber(String sendersBankAccountNumber);

}