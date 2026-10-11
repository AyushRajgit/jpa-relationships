package in.cper.database.PaymentService.service;

import in.cper.database.PaymentService.Enum.PaymentStatus;
import in.cper.database.PaymentService.dto.PaymentRequestDTO;
import in.cper.database.PaymentService.dto.PaymentResponseDTO;
import in.cper.database.PaymentService.dtoMapper.PaymentMapper;
import in.cper.database.PaymentService.entity.BankAccountDetailEntity;
import in.cper.database.PaymentService.entity.CustomerEntity;
import in.cper.database.PaymentService.entity.TransactionRecord;
import in.cper.database.PaymentService.exceptions.InsufficientBalance;
import in.cper.database.PaymentService.exceptions.UserAccountNotFound;
import in.cper.database.PaymentService.repository.BankAccountRepository;
import in.cper.database.PaymentService.repository.CustomerRepository;
import in.cper.database.PaymentService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferPayment {

    private TransactionRepository transactionRepository;
    private BankAccountRepository bankAccountRepository;
    private PaymentMapper paymentMapper;

    @Autowired
    public TransferPayment(TransactionRepository transactionRepository, CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, PaymentMapper paymentMapper) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
        BankAccountDetailEntity senderBankDetails = validateAccount(paymentRequestDTO.getSendersBankAccountNumber());
        BankAccountDetailEntity recipientBankDetails = validateAccount(paymentRequestDTO.getRecipientsBankAccountNumber());

        transactionPossible(senderBankDetails, recipientBankDetails, paymentRequestDTO);

        return paymentMapper.paymentMapper(senderBankDetails);
    }

    private BankAccountDetailEntity validateAccount(String accountNumber) {
        return bankAccountRepository.findByBankAccountNumber(accountNumber).orElseThrow(
                () -> new UserAccountNotFound("No user found with Account-No : " + accountNumber)
        );
    }

    private void transactionPossible(BankAccountDetailEntity senderBankDetails, BankAccountDetailEntity recipientBankDetails, PaymentRequestDTO paymentRequestDTO) {
        if (senderBankDetails.getBalance().compareTo(paymentRequestDTO.getAmount()) <= 0) {
            throw new InsufficientBalance("InsufficientBalance : current balance is less than the amount to be send");
        }

        senderBankDetails.setBalance(senderBankDetails.getBalance().subtract(paymentRequestDTO.getAmount()));
        recipientBankDetails.setBalance(recipientBankDetails.getBalance().add(paymentRequestDTO.getAmount()));

        transactionRepository.save(new TransactionRecord(senderBankDetails.getBankName(), recipientBankDetails.getBankName(), senderBankDetails.getCustomer().getPhoneNumber(), recipientBankDetails.getCustomer().getPhoneNumber(), paymentRequestDTO.getAmount(), PaymentStatus.SUCCESS, senderBankDetails.getBankName(), recipientBankDetails.getBankName()));
        bankAccountRepository.saveAndFlush(senderBankDetails);
    }

}
