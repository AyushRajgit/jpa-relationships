package in.cper.database.ECommerceOrderEngine.repository;

import in.cper.database.ECommerceOrderEngine.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterProductRepository extends JpaRepository<Product, Integer> {

}
