package in.cper.database.ECommerceOrderEngine.repository;

import in.cper.database.ECommerceOrderEngine.entity.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<Orders, Integer> {

}
