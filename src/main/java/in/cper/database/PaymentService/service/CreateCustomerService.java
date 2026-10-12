package in.cper.database.PaymentService.service;

import in.cper.database.ECommerceOrderEngine.exceptions.NotFoundException;
import in.cper.database.PaymentService.dto.AccountDetailsDTO;
import in.cper.database.PaymentService.dto.AccountRequestDTO;
import in.cper.database.PaymentService.dto.CustomerRequestDTO;
import in.cper.database.PaymentService.dto.CustomerResponseDTO;
import in.cper.database.PaymentService.dtoMapper.AccountMapper;
import in.cper.database.PaymentService.dtoMapper.CustomerRegistration_ReqToRes_Mapper;
import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import in.cper.database.PaymentService.entity.CustomerEntity;
import in.cper.database.PaymentService.repository.BankAccountRepository;
import in.cper.database.PaymentService.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CreateCustomerService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private CustomerRegistration_ReqToRes_Mapper ReqRes_Mapper;
    private AccountMapper accountMapper;

    @Autowired
    public CreateCustomerService(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,CustomerRegistration_ReqToRes_Mapper ReqRes_Mapper, AccountMapper accountMapper) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.ReqRes_Mapper = ReqRes_Mapper;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public CustomerResponseDTO registerNewCustomer(CustomerRequestDTO customerRequestDTO) {
        CustomerEntity customerEntity = ReqRes_Mapper.reqToEntityMapper(customerRequestDTO);
        CustomerEntity createdCustomer = customerRepository.save(customerEntity);
        CustomerResponseDTO responseDTO = ReqRes_Mapper.entityToResMapper(createdCustomer);
        return responseDTO;
    }

    public CustomerResponseDTO getCustomer(Long id) {
        CustomerEntity existingCustomer = customerRepository.findById(id).orElseThrow(
                () ->  new NotFoundException("Customer with id:"+ id +"not found : while fetching")
        );
        CustomerResponseDTO responseDTO = ReqRes_Mapper.entityToResMapper(existingCustomer);
        return responseDTO;
    }

    public Set<AccountDetailsDTO> getAccountDetails(Long id) {
        CustomerEntity existingCustomer = customerRepository.findById(id).orElseThrow(
                () ->  new NotFoundException("Customer with id:"+ id +"not found while fetching account details")
        );
        Set<AccountDetailsDTO> accountRes = ReqRes_Mapper.entityToAccountResMapper(existingCustomer.getBankAccounts());
        return accountRes;
    }

    @Transactional
    public void updatePassKey(Long customerId, Long accountId, Long newPassKey) {
        BankAccountDetailEntity account = bankAccountRepository.findById(accountId).orElseThrow(
                () ->  new NotFoundException("Account with id:"+ accountId +" not found while changing passkey")
        );

        account.getAccountPassKey().setPassKey(newPassKey);
        bankAccountRepository.save(account);
    }

    @Transactional
    public String addAccount(Long customerId, AccountRequestDTO accountRequestDTO) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                () ->  new NotFoundException("Customer with id:"+ customerId +"not found")
        );

        BankAccountDetailEntity account = accountMapper.accountReqToBankAccount(accountRequestDTO, customer);
        customer.getBankAccounts().add(account);
        customerRepository.save(customer);

        return "Account created successfully";
    }
}
