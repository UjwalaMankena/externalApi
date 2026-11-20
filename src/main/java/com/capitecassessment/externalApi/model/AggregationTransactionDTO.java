package com.capitecassessment.externalApi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record AggregationTransactionDTO(
            String id,                // Deterministic Idempotency Key (SHA-256 of source+ref+amount+date)
            String accountNumber,
            BigDecimal amount,
            String currency,
            String description,
            LocalDateTime timestamp,
            String sourceInstitution,
            TransactionDTOCategory category
    ) {}

