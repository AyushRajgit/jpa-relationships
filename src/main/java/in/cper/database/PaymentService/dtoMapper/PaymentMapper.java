package in.cper.database.PaymentService.dtoMapper;

import in.cper.database.PaymentService.dto.PaymentRequestDTO;
import in.cper.database.PaymentService.dto.PaymentResponseDTO;
import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDTO paymentMapper(BankAccountDetailEntity bankAccountDetailEntity) {
        return new  PaymentResponseDTO(
                "Payment successful!",
                bankAccountDetailEntity.getBankName(),
                bankAccountDetailEntity.getBankAccountNumber(),
                bankAccountDetailEntity.getBalance()
        );
    }
}
