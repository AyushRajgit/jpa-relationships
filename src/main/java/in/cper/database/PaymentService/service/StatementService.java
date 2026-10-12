package in.cper.database.PaymentService.service;

import in.cper.database.PaymentService.dto.StatementDTO;
import in.cper.database.PaymentService.dtoMapper.StatementMapper;
import in.cper.database.PaymentService.entity.TransactionRecord;
import in.cper.database.PaymentService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<StatementDTO> getStatement(Long customerId, int pageNo, int pageSize) {
        List<TransactionRecord> transactionRecords = transactionRepository.findBySenderCustomer_CustomerId(customerId);

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<TransactionRecord> page = transactionRepository.findAll(pageable);

        List<StatementDTO> statementDTOs = new ArrayList<>();
        for (TransactionRecord transactionRecord : page.getContent()) {
            statementDTOs.add(statementMapper.transactionToStatementDTO(transactionRecord));
        }

        return statementDTOs;
    }
}
