package com.capitecassessment.externalApi.controller;

import com.capitecassessment.externalApi.exception.AccountNumberNotFoundException;
import com.capitecassessment.externalApi.exception.GlobalExceptionHandler;
import com.capitecassessment.externalApi.model.AggregationTransactionDTO;
import com.capitecassessment.externalApi.service.MockDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/externalapi/v1/transactions")
public class ExternalApiController {


    private final GlobalExceptionHandler globalExceptionHandler;

    public ExternalApiController(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @GetMapping("/external")
    public ResponseEntity getExternalTransactions(@RequestParam String accountNumber, WebRequest webReq) {
        try {
            log.info("In getExternalTransactions");
            MockDataService sourceTx = new MockDataService();

            BigDecimal constant = new BigDecimal("1234567890");
           //Modifiable Arraylist to add elements to another list
            if(constant.equals(new BigDecimal(accountNumber.substring(1,accountNumber.length()-1)))) {
                List<AggregationTransactionDTO> transactionDTOs = new ArrayList<>(sourceTx.fetchChequeTransactions(accountNumber));
                List<AggregationTransactionDTO> transactionDTOs1 = new ArrayList<>(sourceTx.fetchCreditTransactions(accountNumber));
                boolean b = transactionDTOs.addAll(transactionDTOs1);
                log.info("created final list" + b);
                return ResponseEntity.ok(transactionDTOs);

            }else{

                throw new AccountNumberNotFoundException("Account Number not found");
            }

        } catch(AccountNumberNotFoundException ae) {

            return globalExceptionHandler.handleAccountNumberNotFoundException(ae, webReq);

        }catch(Exception e){
            return globalExceptionHandler.handleGlobalException(e,webReq);
        }

    }

    @GetMapping("/cheque")
    public ResponseEntity getChequeTransactions(@RequestParam String accountNumber, WebRequest webReq) {
        try {
            log.info("In getChequeTransactions");
            MockDataService sourceTx = new MockDataService();

            BigDecimal constant = new BigDecimal("1234567890");
            //Modifiable Arraylist to add elements to another list
            if(constant.equals(new BigDecimal(accountNumber.substring(1,accountNumber.length()-1)))) {
                List<AggregationTransactionDTO> transactionDTOs = new ArrayList<>(sourceTx.fetchChequeTransactions(accountNumber));
               //// List<AggregationTransactionDTO> transactionDTOs1 = new ArrayList<>(sourceTx.fetchCreditTransactions(accountNumber));
               // boolean b = transactionDTOs.addAll(transactionDTOs1);
               // log.info("created final list" + b);
                return ResponseEntity.ok(transactionDTOs);

            }else{

                throw new AccountNumberNotFoundException("Account Number not found");
            }

        } catch(AccountNumberNotFoundException ae) {

            return globalExceptionHandler.handleAccountNumberNotFoundException(ae, webReq);

        }catch(Exception e){
            return globalExceptionHandler.handleGlobalException(e,webReq);
        }

    }

    @GetMapping("/credit")
    public ResponseEntity getCreditTransactions(@RequestParam String accountNumber, WebRequest webReq) {
        try {
            log.info("In getCreditTransactions");
            MockDataService sourceTx = new MockDataService();

            BigDecimal constant = new BigDecimal("1234567890");
            //Modifiable Arraylist to add elements to another list
            if(constant.equals(new BigDecimal(accountNumber.substring(1,accountNumber.length()-1)))) {
               // List<AggregationTransactionDTO> transactionDTOs = new ArrayList<>(sourceTx.fetchChequeTransactions(accountNumber));
                List<AggregationTransactionDTO> transactionDTOs1 = new ArrayList<>(sourceTx.fetchCreditTransactions(accountNumber));
               // boolean b = transactionDTOs.addAll(transactionDTOs1);
                //log.info("created final list" + b);
                return ResponseEntity.ok(transactionDTOs1);

            }else{

                throw new AccountNumberNotFoundException("Account Number not found");
            }

        } catch(AccountNumberNotFoundException ae) {

            return globalExceptionHandler.handleAccountNumberNotFoundException(ae, webReq);

        }catch(Exception e){
            return globalExceptionHandler.handleGlobalException(e,webReq);
        }

    }




}
