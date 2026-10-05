package in.cper.database.ECommerceOrderEngine.controller;

import in.cper.database.ECommerceOrderEngine.entity.Customer;
import in.cper.database.ECommerceOrderEngine.service.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amazon/register-customer")
public class RegisterCustomerController {

    private RegisterCustomerService registerCustomerService;

    @Autowired
    public RegisterCustomerController(RegisterCustomerService registerCustomerService) {
        this.registerCustomerService = registerCustomerService;
    }

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        registerCustomerService.register(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<Customer>> registerAllCustomers(@RequestBody List<Customer> customers) {
        registerCustomerService.registerAll(customers);
        return ResponseEntity.status(HttpStatus.CREATED).body(customers);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> fetchedCustomers = registerCustomerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(fetchedCustomers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Customer fetchedCustomer = registerCustomerService.get(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedCustomer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable int customerId) {
        registerCustomerService.remove(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer with id " + customerId + " has been deleted");
    }
}
