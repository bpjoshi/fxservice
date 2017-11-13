package com.bpjoshi.fxservice.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bpjoshi.fxservice.exception.DataFormatException;
import com.bpjoshi.fxservice.model.Trade;
/**
 * 
 * @author bpjoshi(Bhagwati Prasad)
 *  Validation for Trade Class
 *  I didn't write else case, thinking a user might update the data and while updating these validation will run again.
 */
@Component
public class TradeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Trade.class.equals(clazz);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		//todo
	}
	
	public void validate(Object obj) {
		Trade trade=(Trade) obj;
		//TCN should not be null or blank
		if(trade.getTCN() == null && trade.getTCN()=="") {
	          throw new DataFormatException("TCN shouldn't be null or empty");
	      }
		//Value date & trade date validation
		if(trade.getValueDate()!=null && trade.getTradeDate()!=null && trade.getValueDate().before(trade.getTradeDate())){
			throw new DataFormatException("Value date shouldn't be before trade date for trade TCN: "+trade.getTCN());
		}
		//Counter party validation
		if(trade.getCustomer()!=null){
			if(!(trade.getCustomer().equalsIgnoreCase("PLUTO1") ||trade.getCustomer().equalsIgnoreCase("PLUTO2")) ){
				throw new DataFormatException("The counter party is not registered for trade TCN: "+trade.getTCN());
			}
		}

		//Validate currencies for valid ISO code
		if(trade.getCCY()!=null){
			if(!(trade.getCCY().equals(ISOCodesEnum.USD_ISO) || trade.getCCY().equals(ISOCodesEnum.EURO_ISO) )){
				throw new DataFormatException("Currency code should a valid one (USD/EUR) for trade TCN: "+trade.getTCN());
			}
		}
		//Validate valueDate against type (must be either spot or forward (assumptions))
		if(trade.getValueDate()!=null){
			if(!(trade.getType().equalsIgnoreCase("SPOT")||trade.getType().equalsIgnoreCase("FORWARD"))){
				throw new DataFormatException("Type should be spot or forward if value date is present for trade TCN: "+trade.getTCN());
			}
		}
		//Check currency Style
		if(trade.getStyle()!=null){
			if(!(trade.getStyle().equalsIgnoreCase("EUROPEAN") || trade.getStyle().equalsIgnoreCase("American"))){
				throw new DataFormatException("Style should be either American or European for trade TCN: "+trade.getTCN());
			}
		}
		
		
		//Validate exercise start date ..
		if(trade.getStyle()!=null){
			if(trade.getStyle().equalsIgnoreCase("American")){
				if(trade.getExerciseStartDate()!=null && trade.getTradeDate()!=null && trade.getExpiryDate()!=null){
					if(trade.getExerciseStartDate().before(trade.getTradeDate()) || 
							trade.getExerciseStartDate().after(trade.getExpiryDate())){
						throw new DataFormatException("ExcerciseStartDate should be between trade date and expiry date for trade TCN: "+trade.getTCN());
					}
				}
			}
		}
		
		//Expiry date and premium date shall be before delivery date
		if(trade.getExerciseStartDate()!=null && trade.getDeliveryDate()!=null && trade.getPremiumDate()!=null){
			if(trade.getExpiryDate().after(trade.getDeliveryDate())||trade.getPremiumDate().after(trade.getDeliveryDate())){
				throw new DataFormatException("Expiry date and premium date should be before delivey date for trade TCN: "+trade.getTCN());
			}
		}
		
	}

}
