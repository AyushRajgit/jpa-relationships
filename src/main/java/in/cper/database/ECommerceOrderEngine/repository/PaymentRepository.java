package in.cper.database.ECommerceOrderEngine.repository;

import in.cper.database.ECommerceOrderEngine.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
