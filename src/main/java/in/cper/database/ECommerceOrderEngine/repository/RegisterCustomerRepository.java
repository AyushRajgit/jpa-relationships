package in.cper.database.ECommerceOrderEngine.repository;

import in.cper.database.ECommerceOrderEngine.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterCustomerRepository extends JpaRepository<Customer,Integer> {

}
