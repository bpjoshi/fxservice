package com.bpjoshi.fxservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bpjoshi.fxservice.model.Trade;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Repository Interface for Trade
 */
public interface TradeRepository extends PagingAndSortingRepository<Trade, String>{
	 Page<Trade> findAll(Pageable pageable);
}
