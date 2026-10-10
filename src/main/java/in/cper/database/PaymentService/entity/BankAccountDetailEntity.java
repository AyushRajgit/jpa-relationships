package in.cper.database.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class BankAccountDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankAccountId;

    private String bankName;
    private String bankAccountNumber;
    private BigDecimal balance;

    @Embedded
    private AccountPassKey accountPassKey;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;
}
