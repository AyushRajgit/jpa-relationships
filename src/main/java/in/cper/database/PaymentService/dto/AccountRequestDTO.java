package in.cper.database.PaymentService.dto;

import in.cper.database.PaymentService.entity.AccountPassKey;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountRequestDTO {
    private String bankName;
    private String bankAccountNumber;
    private BigDecimal balance;

    @Embedded
    private AccountPassKey accountPassKey;
}
