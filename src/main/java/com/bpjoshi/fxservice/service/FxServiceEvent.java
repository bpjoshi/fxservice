package com.bpjoshi.fxservice.service;

import org.springframework.context.ApplicationEvent;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Publishing application events, like to inject events into the Spring 
 * Boot audit management
 */
public class FxServiceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public FxServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "FxService Event";
    }

}
