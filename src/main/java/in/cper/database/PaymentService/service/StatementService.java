package in.cper.database.PaymentService.service;

import in.cper.database.PaymentService.dto.StatementDTO;
import in.cper.database.PaymentService.dtoMapper.StatementMapper;
import in.cper.database.PaymentService.entity.TransactionRecord;
import in.cper.database.PaymentService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {

    private TransactionRepository transactionRepository;
    private StatementMapper statementMapper;

    @Autowired
    public StatementService(TransactionRepository transactionRepository, StatementMapper statementMapper) {
        this.transactionRepository = transactionRepository;
        this.statementMapper = statementMapper;
    }

    public List<StatementDTO> getStatement(Long customerId) {
        List<TransactionRecord> transactionRecords = transactionRepository.findByCustomerId(customerId);

        List<StatementDTO> statementDTOs = new ArrayList<>();
        for (TransactionRecord transactionRecord : transactionRecords) {
            statementDTOs.add(statementMapper.transactionToStatementDTO(transactionRecord));
        }

        return statementDTOs;
    }
}
