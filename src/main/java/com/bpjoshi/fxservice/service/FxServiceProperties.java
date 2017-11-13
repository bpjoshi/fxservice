package com.bpjoshi.fxservice.service;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Injecting Service Specific Projecties
 */
@ConfigurationProperties(prefix = "fx.service", ignoreUnknownFields = false)
@Component
public class FxServiceProperties {

    @NotNull
	private String name = "Empty";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
