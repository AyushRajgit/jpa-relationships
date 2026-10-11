package in.cper.database.PaymentService.controller;

import in.cper.database.PaymentService.dto.CustomerRequestDTO;
import in.cper.database.PaymentService.dto.CustomerResponseDTO;
import in.cper.database.PaymentService.dto.PaymentRequestDTO;
import in.cper.database.PaymentService.dto.PaymentResponseDTO;
import in.cper.database.PaymentService.service.TransferPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amazonPay/transferPayment")
public class PaymentController {

    private TransferPayment transferPayment;

    @Autowired
    public PaymentController(TransferPayment transferPayment) {
        this.transferPayment = transferPayment;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> transferPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO paymentResponseDTO = transferPayment.pay(paymentRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResponseDTO);
    }
}
