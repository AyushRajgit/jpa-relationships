package in.cper.database.ECommerceOrderEngine.service;

import in.cper.database.ECommerceOrderEngine.entity.CartItem;
import in.cper.database.ECommerceOrderEngine.entity.Product;
import in.cper.database.ECommerceOrderEngine.entity.ShoppingCart;
import in.cper.database.ECommerceOrderEngine.exceptions.NoStockAvailableException;
import in.cper.database.ECommerceOrderEngine.exceptions.NotFoundException;
import in.cper.database.ECommerceOrderEngine.repository.CartItemRepository;
import in.cper.database.ECommerceOrderEngine.repository.RegisterCustomerRepository;
import in.cper.database.ECommerceOrderEngine.repository.RegisterProductRepository;
import in.cper.database.ECommerceOrderEngine.repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShopItemService {

    private ShopItemRepository shopItemRepository;
    private RegisterProductRepository registerProductRepository;
    private RegisterCustomerRepository registerCustomerRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public ShopItemService(ShopItemRepository shopItemRepository, RegisterProductRepository registerProductRepository, RegisterCustomerRepository registerCustomerRepository, CartItemRepository cartItemRepository) {
        this.shopItemRepository = shopItemRepository;
        this.registerProductRepository = registerProductRepository;
        this.registerCustomerRepository = registerCustomerRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public Set<CartItem> addToCart(int customerId, int productId, int quantity) {
        Product product = registerProductRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product with id :" + productId + "not found while adding into cart")
        );

        ShoppingCart shoppingCart = shopItemRepository.findById(customerId).orElse(new ShoppingCart());

        CartItem newCartItem = shoppingCart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getProductId() == productId)
                .findFirst()
                .orElse(new CartItem());

        newCartItem.setProduct(product);
        if (product.getAvailableQuantity() >= quantity) {
            newCartItem.setQuantity(newCartItem.getQuantity() + quantity);
//            product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
        } else {
            throw new NoStockAvailableException("Demanded quantity for product is more than available stock");
        }
        newCartItem.setPriceAtCheckout(product.getProductPrice()
                .multiply(BigDecimal.valueOf(newCartItem.getQuantity())));
        newCartItem.setShoppingCart(shoppingCart);

        shoppingCart.getCartItems().add(newCartItem);
        shoppingCart.setCustomer(registerCustomerRepository.findById(customerId).orElseThrow(
                () -> new NotFoundException("Customer not found associated to customerId : " + customerId + " while adding customer details to cart")
        ));

        cartItemRepository.save(newCartItem);
        shopItemRepository.save(shoppingCart);

        return shoppingCart.getCartItems();
    }

    public Set<CartItem> getCartItems(int customerId) {
        ShoppingCart shoppingCart = shopItemRepository.findById(customerId).orElseThrow(
                () -> new NotFoundException("No cart found for the customer with id : " + customerId)
        );

        Set<CartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new NotFoundException("Shopping cart with no cart items found for the customer with id : " + customerId);
        }

        return cartItems;
    }

    @Transactional
    public Set<CartItem> removeFromCart(int customerId, int productId) {
        ShoppingCart shoppingCart = shopItemRepository.findById(customerId).orElseThrow(
                () -> new NotFoundException("No shopping cart found")
        );

        int size = shoppingCart.getCartItems().size();
        CartItem targetRef = null;
        for (CartItem item : shoppingCart.getCartItems()) {
            if (item.getProduct().getProductId() == productId) {
                targetRef = item;
                break;
            }
        }

        if (targetRef != null) {
            if (shoppingCart.getCartItems().contains(targetRef)) shoppingCart.getCartItems().remove(targetRef);
            cartItemRepository.delete(targetRef);
        } else {
            throw new NotFoundException("Product with id :" + productId + " for customer with id :" + " not found while removing from cart");
        }

        shopItemRepository.save(shoppingCart);
        return shoppingCart.getCartItems();
    }

}
