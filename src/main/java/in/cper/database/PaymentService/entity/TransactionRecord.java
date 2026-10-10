package in.cper.database.PaymentService.entity;

import in.cper.database.PaymentService.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionRecordId;

    @Column(updatable = false, nullable = false)
    private String transactionId;

    private Long  customerId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String senderName;
    private String receiverName;
    private String senderPhoneNumber;
    private String receiverPhoneNumber;
    private LocalDateTime dateAndTime;
    private PaymentStatus status;
    private String sendersBankName;
    private String receiversBankName;
    private BigDecimal amount;

    @PrePersist
    public void onCreate() {
        this.transactionId = UUID.randomUUID().toString();
        dateAndTime = LocalDateTime.now();
    }

    public TransactionRecord(String senderName, String receiverName, String senderPhoneNumber, String receiverPhoneNumber, BigDecimal amount, PaymentStatus status, String sendersBankName, String receiversBankName) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.amount = amount;
        this.status = status;
        this.sendersBankName = sendersBankName;
        this.receiversBankName = receiversBankName;
    }
}
