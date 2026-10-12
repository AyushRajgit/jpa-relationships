package in.cper.database.PaymentService.dtoMapper;

import in.cper.database.PaymentService.dto.StatementDTO;
import in.cper.database.PaymentService.entity.TransactionRecord;
import org.springframework.stereotype.Component;

@Component
public class StatementMapper {
    public StatementDTO transactionToStatementDTO(TransactionRecord transaction) {
        StatementDTO statementDTO = new StatementDTO(
                transaction.getTransactionId(),
                transaction.getSenderName(),
                transaction.getReceiverName(),
                transaction.getSenderPhoneNumber(),
                transaction.getReceiverPhoneNumber(),
                transaction.getDateAndTime(),
                transaction.getPaymentStatus(),
                transaction.getSendersBankName(),
                transaction.getReceiversBankName(),
                transaction.getAmount()
        );

        return statementDTO;
    }
}
