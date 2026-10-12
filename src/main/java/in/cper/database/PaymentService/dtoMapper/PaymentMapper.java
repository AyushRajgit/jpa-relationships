package in.cper.database.PaymentService.dtoMapper;

import in.cper.database.PaymentService.dto.PaymentRequestDTO;
import in.cper.database.PaymentService.dto.PaymentResponseDTO;
import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDTO paymentMapper(BankAccountDetailEntity sender, BankAccountDetailEntity receiver) {
        return new  PaymentResponseDTO(
                "Payment successful!",
                "Paid to " + receiver.getBankAccountNumber() + " from " + sender.getBankAccountNumber(),
                sender.getBankName(),
                sender.getBankAccountNumber(),
                sender.getBalance()
        );
    }
}
