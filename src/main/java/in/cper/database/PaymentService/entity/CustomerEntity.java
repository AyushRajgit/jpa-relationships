package in.cper.database.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<BankAccountDetailEntity> bankAccounts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<TransactionRecord> transactions;

    public CustomerEntity(String firstName, String lastName, String phoneNumber, Set<BankAccountDetailEntity> bankAccounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.bankAccounts = bankAccounts;
    }
}
