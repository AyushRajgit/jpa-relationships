package in.cper.database.ECommerceOrderEngine.repository;

import in.cper.database.ECommerceOrderEngine.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemRepository extends JpaRepository<ShoppingCart, Integer> {

}
