package com.bpjoshi.fxservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bpjoshi.fxservice.model.Trade;
import com.bpjoshi.fxservice.repository.TradeRepository;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public TradeService() {
    }

    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Trade getTrade(String id) {
        return tradeRepository.findOne(id);
    }

    public void updateTrade(Trade trade) {
    	tradeRepository.save(trade);
    }

    public void deleteTrade(String id) {
    	tradeRepository.delete(id);
    }

    public Page<Trade> getAllTrades(Integer page, Integer size) {
        Page<Trade> pageOfTrades = tradeRepository.findAll(new PageRequest(page, size));
        return pageOfTrades;
    }

	public Iterable<Trade> createTrades(List<Trade> tradeList) {
		return tradeRepository.save(tradeList);
	}
}
