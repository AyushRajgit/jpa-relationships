package in.cper.database.ECommerceOrderEngine.controller;

import in.cper.database.ECommerceOrderEngine.entity.Product;
import in.cper.database.ECommerceOrderEngine.service.RegisterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amazon/register-product")
public class RegisterProductController {

    private RegisterProductService registerProductService;

    @Autowired
    private RegisterProductController(RegisterProductService registerProductService) {
        this.registerProductService = registerProductService;
    }

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody Product product){
        registerProductService.register(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/addAll")
    public ResponseEntity<Product> registerAllProduct(@RequestBody List<Product> products){
        registerProductService.registerAll(products);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = registerProductService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId){
        Product product = registerProductService.get(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PatchMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updatedProduct = registerProductService.update(product);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@RequestParam int productId){
        registerProductService.remove(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product with id " + productId + " has been deleted");
    }
}
