package in.cper.database.ECommerceOrderEngine.controller;

import in.cper.database.ECommerceOrderEngine.dto.CheckOutDTO;
import in.cper.database.ECommerceOrderEngine.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/amazon/checkout")
public class CheckOutController {

    private CheckOutService  checkOutService;

    @Autowired
    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping
    public ResponseEntity<CheckOutDTO> checkOut(@RequestParam int customerId) {
        CheckOutDTO checkOutDTO = checkOutService.performCheckOut(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(checkOutDTO);
    }
}
