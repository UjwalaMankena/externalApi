package com.capitecassessment.externalApi.service;

import com.capitecassessment.externalApi.model.AggregationTransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockDataService {


    public List<AggregationTransactionDTO> fetchChequeTransactions(String accountNumber) {
       simulateNetworkLatency(120); // 120ms delay
        log.info("In fetch cheque transactions");
        return List.of(
                new AggregationTransactionDTO(UUID.randomUUID().toString(), accountNumber, new BigDecimal("-450.00"), "ZAR", "PICK N PAY SUPERMARKET", LocalDateTime.now().minusHours(2), "CHEQUE_PROD", null),
                new AggregationTransactionDTO(UUID.randomUUID().toString(), accountNumber, new BigDecimal("25000.00"), "ZAR", "CAPITEC SALARY", LocalDateTime.now().minusDays(1), "CHEQUE_PROD", null)
        );
    }

    public List<AggregationTransactionDTO> fetchCreditTransactions(String accountNumber) {
        simulateNetworkLatency(280); // Flaky/slower service
        log.info("In fetch credit transactions");

        return List.of(
                new AggregationTransactionDTO(UUID.randomUUID().toString(), accountNumber, new BigDecimal("-1200.00"), "ZAR", "WOOLWORTHS ONLINE", LocalDateTime.now().minusHours(5), "CREDIT_PROD", null),
                new AggregationTransactionDTO(UUID.randomUUID().toString(), accountNumber, new BigDecimal("-450.00"), "ZAR", "PICK N PAY SUPERMARKET", LocalDateTime.now().minusHours(2), "CREDIT_PROD", null) // Intentional duplicate test case
        );
    }

    private void simulateNetworkLatency(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

}
