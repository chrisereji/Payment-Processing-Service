package com.cpt.payments.service.recon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cpt.payments.dao.interfaces.TransactionDao;
import com.cpt.payments.dto.TransactionDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReconService {
	
	@Autowired
	private ReconTransactionAsync reconTxnAsync;
	
	@Autowired
	private TransactionDao txnDao;
	
	// Runs every 15 minutes
    @Scheduled(cron = "0 0/1 * * * ?")
    public void performTask() {
        log.info("Task executed");
        
        List<TransactionDTO> txnsForRecon = txnDao.fetchTransactionsForReconcilation();
        
        log.info("About to process recon for txnsForRecon.size:" + txnsForRecon.size());
        
        for (TransactionDTO item : txnsForRecon) {
        	log.trace("submit task for async execution|item:" + item);
        	reconTxnAsync.processItem(item);
        }
        
    }

}
