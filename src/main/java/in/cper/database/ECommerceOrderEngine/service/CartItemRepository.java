package in.cper.database.ECommerceOrderEngine.service;

import in.cper.database.ECommerceOrderEngine.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
