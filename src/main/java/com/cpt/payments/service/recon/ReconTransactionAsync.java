package com.cpt.payments.service.recon;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cpt.payments.dto.TransactionDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReconTransactionAsync {

	/**
	 * All transaction of every PSP will come to this place.
	 * Every PSP - recon process Stripe different than other PSP
	 * 
	 * For given txn we get the PSP specific Handler from ProviderFactory
	 * And invoke reconTnx for processing the reconcilation process.
	 * @param txnDto
	 */
	@Async
	public void processItem(TransactionDTO txnDto) {
		log.info("Processing item:" + txnDto);
		
		txnDto.getProvider();
	}

}
