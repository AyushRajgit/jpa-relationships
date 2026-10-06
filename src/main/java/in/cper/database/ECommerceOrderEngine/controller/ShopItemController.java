package in.cper.database.ECommerceOrderEngine.controller;

import in.cper.database.ECommerceOrderEngine.dto.ShoppingCartDTO;
import in.cper.database.ECommerceOrderEngine.entity.CartItem;
import in.cper.database.ECommerceOrderEngine.service.CheckOutService;
import in.cper.database.ECommerceOrderEngine.service.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/amazon/shopping")
public class ShopItemController {

    private ShopItemService shopItemService;

    @Autowired
    public ShopItemController(ShopItemService shopItemService) {
        this.shopItemService = shopItemService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<ShoppingCartDTO> addToCart(@RequestParam int customerId, @RequestParam int productId, @RequestParam int quantity) {
        Set<CartItem> cartItem = shopItemService.addToCart(customerId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShoppingCartDTO(
                cartItem,
                "Successfully added to your cart! Enjoy your shopping"
        ));
    }

    @GetMapping("/viewMyCart")
    public ResponseEntity<ShoppingCartDTO> viewMyCart(@RequestParam int customerId) {
        Set<CartItem> myCartItems = shopItemService.getCartItems(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ShoppingCartDTO(
                myCartItems,
                "Here, is your shopping cart! checkout so it could reach at your door asap"
        ));
    }

    @DeleteMapping("/removeFromCart")
    public ResponseEntity<ShoppingCartDTO> removeFromCart(@RequestParam int customerId, @RequestParam int productId) {
        Set<CartItem> freshCartItem = shopItemService.removeFromCart(customerId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ShoppingCartDTO(
                freshCartItem,
                "Successfully removed product from your cart! Enjoy your shopping"
        ));
    }
}
