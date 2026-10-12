package in.cper.database.PaymentService.controller;

import in.cper.database.PaymentService.dto.*;
import in.cper.database.PaymentService.service.CreateCustomerService;
import in.cper.database.PaymentService.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/amazonPay/Customer")
public class CreateCustomerController {

    private CreateCustomerService createCustomerService;
    private StatementService statementService;

    @Autowired
    public CreateCustomerController(CreateCustomerService createCustomerService, StatementService statementService) {
        this.createCustomerService = createCustomerService;
        this.statementService = statementService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> registerNewCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO newCustomer = createCustomerService.registerNewCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestParam Long customerId, @RequestBody AccountRequestDTO accountRequestDTO) {
        String response = createCustomerService.addAccount(customerId, accountRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        CustomerResponseDTO existingCustomer = createCustomerService.getCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingCustomer);
    }

    @GetMapping("/getAccountDetails/{id}")
    public ResponseEntity<Set<AccountDetailsDTO>> getAccountDetails(@PathVariable Long id) {
        Set<AccountDetailsDTO> accountDetails = createCustomerService.getAccountDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(accountDetails);
    }

    @GetMapping("/statement")
    public ResponseEntity<List<StatementDTO>> getStatement(@RequestParam Long id, @RequestParam int pageNo, @RequestParam int pageSize) {
        List<StatementDTO> statement = statementService.getStatement(id, pageNo, pageSize);
        return  ResponseEntity.status(HttpStatus.OK).body(statement);
    }

    @PatchMapping("/changePassKey")
    public ResponseEntity<String> updatePassKey(@RequestParam Long customerId, @RequestParam Long accountId, @RequestParam Long newPassKey) {
        createCustomerService.updatePassKey(customerId, accountId, newPassKey);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }


}
