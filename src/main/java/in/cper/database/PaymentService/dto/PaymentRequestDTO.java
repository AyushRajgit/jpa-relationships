package in.cper.database.PaymentService.dto;

import in.cper.database.PaymentService.Enum.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRequestDTO {
    private String sendersBankAccountNumber;
    private String recipientsBankAccountNumber;
    private Long passKey;
    private BigDecimal amount;
}
