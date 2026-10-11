package in.cper.database.PaymentService.dtoMapper;

import in.cper.database.PaymentService.dto.AccountRequestDTO;
import in.cper.database.PaymentService.entity.AccountPassKey;
import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import in.cper.database.PaymentService.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountDetailEntity accountReqToBankAccount(AccountRequestDTO accountRequestDTO, CustomerEntity customer) {
        return new BankAccountDetailEntity(
                accountRequestDTO.getBankName(),
                accountRequestDTO.getBankAccountNumber(),
                accountRequestDTO.getBalance(),
                accountRequestDTO.getAccountPassKey(),
                customer
        );
    }
}
