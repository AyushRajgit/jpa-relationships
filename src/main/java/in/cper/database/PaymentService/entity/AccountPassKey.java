package in.cper.database.PaymentService.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class AccountPassKey {
    private Long passKey;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @PrePersist
    public void onCreation() {
        createdDateTime = LocalDateTime.now();
        updatedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedDateTime = LocalDateTime.now();
    }

}
