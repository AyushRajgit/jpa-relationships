package in.cper.database.PaymentService.dtoMapper;

import in.cper.database.PaymentService.dto.AccountDetailsDTO;
import in.cper.database.PaymentService.dto.CustomerRequestDTO;
import in.cper.database.PaymentService.dto.CustomerResponseDTO;
import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import in.cper.database.PaymentService.entity.CustomerEntity;

import java.util.HashSet;
import java.util.Set;

public class CustomerRegistration_ReqToRes_Mapper {
    public CustomerResponseDTO entityToResMapper(CustomerEntity customerEntity) {
        CustomerResponseDTO res = new CustomerResponseDTO(
                "User created successfully! Enjoy our payment service receive assured cashback...",
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getPhoneNumber(),
                customerEntity.getBankAccounts()
        );
        return res;
    }

    public CustomerEntity reqToEntityMapper(CustomerRequestDTO req) {
        CustomerEntity customerEntity = new CustomerEntity(
                req.getFirstName(),
                req.getLastName(),
                req.getPhoneNumber(),
                req.getBankAccounts()
        );
        return customerEntity;
    }

    public Set<AccountDetailsDTO> entityToAccountResMapper(Set<BankAccountDetailEntity> bankAccountDetail) {
        Set<AccountDetailsDTO> res = new HashSet<>();
        for (BankAccountDetailEntity account : bankAccountDetail) {
            res.add(new AccountDetailsDTO(
                    account.getBankName(),
                    account.getBankAccountNumber(),
                    account.getBalance()
            ));
        }
        return res;
    }
}
