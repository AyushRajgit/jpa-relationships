package in.cper.database.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Embeddable
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<BankAccountDetailEntity> bankAccounts;

    public CustomerEntity(String firstName, String lastName, String phoneNumber, Set<BankAccountDetailEntity> bankAccounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.bankAccounts = bankAccounts;
    }
}
