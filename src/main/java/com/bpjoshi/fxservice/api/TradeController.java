package com.bpjoshi.fxservice.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bpjoshi.fxservice.exception.DataFormatException;
import com.bpjoshi.fxservice.model.Trade;
import com.bpjoshi.fxservice.service.TradeService;
import com.bpjoshi.fxservice.validator.TradeValidator;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Sets up REST resource URIs
 */
@RestController
@RequestMapping(value = "/fxservice/trades")
@Api(tags = {"trades"})
public class TradeController extends AbstractHandler {

    private final TradeService tradeService;
    
    private final TradeValidator tradeValidator;
    
    @Autowired
    TradeController(TradeService tradeService, TradeValidator tradeValidator){
    	this.tradeService=tradeService;
    	this.tradeValidator=tradeValidator;
    }
    
    /**
     * @param trades
     * @param request
     * @param response
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create trades.", notes = "Returns link of get all")
    public void createTradeCollection(@RequestBody List<Trade> tradeList, HttpServletRequest request, HttpServletResponse response) {
    	for(Trade trade: tradeList){
    		tradeValidator.validate(trade);
    		tradeService.createTrade(trade);
    	}
    	
    	
        response.setHeader("Location", request.getRequestURL().append("/").toString());
    }
    
   
    
    
    
    /**
     * @param page
     * @param size
     * @param request
     * @param response
     * @return
     */
   @RequestMapping(value = "",method = RequestMethod.GET,produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all trades.", notes = "The is a paginated list. Default page number is 0 & page size 100")
    public @ResponseBody Page<Trade> getAllTrade(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.tradeService.getAllTrades(page, size);
    }
   
   	/**
   	 * @param TCN
   	 * @param request
   	 * @param response
   	 * @return
   	 * @throws Exception
   	 */
    @RequestMapping(value = "/{TCN}",method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single trade.", notes = "You have to provide a valid trade TCN.")
    public
    @ResponseBody
    Trade getTrade(@ApiParam(value = "The ID of the trade.", required = true)
                             @PathVariable("TCN") String TCN,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Trade trade = this.tradeService.getTrade(TCN);
        checkResourceFound(trade);
        return trade;
    }
    
    /**
     * @param TCN
     * @param trade
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{TCN}",
            method = RequestMethod.PUT,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a trade resource.", notes = "You have to provide a valid trade TCN in the URI and in the payload. TCN can't be updated")
    public void updateTrade(@ApiParam(value = "TCN of the existing trade resource.", required = true)
                                 @PathVariable("TCN") String TCN, @RequestBody Trade trade,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.tradeService.getTrade(TCN));
        if (TCN.equals(trade.getTCN())) throw new DataFormatException("TCN doesn't match!");
        tradeValidator.validate(trade);
        tradeService.updateTrade(trade);
    }
    
    /**
     * @param TCN
     * @param request
     * @param response
     */
    @RequestMapping(value = "/{TCN}",
            method = RequestMethod.DELETE,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a trade resource.", notes = "You have to provide a valid trade TCN in the URI. Once deleted the trade info can not be recovered.")
    public void deleteTrade(@ApiParam(value = "TCN of the existing trade resource.", required = true)
                                 @PathVariable("TCN") String TCN, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.tradeService.getTrade(TCN));
        this.tradeService.deleteTrade(TCN);
    }
}
