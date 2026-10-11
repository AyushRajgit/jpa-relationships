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

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    public BankAccountDetailEntity(String bankName, String bankAccountNumber, BigDecimal balance, AccountPassKey accountPassKey, CustomerEntity customer) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
        this.accountPassKey = accountPassKey;
        this.customer = customer;
        this.accountPassKey = accountPassKey;
    }
}
