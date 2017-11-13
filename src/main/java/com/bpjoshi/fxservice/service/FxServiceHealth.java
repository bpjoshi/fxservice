package com.bpjoshi.fxservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import com.bpjoshi.fxservice.service.FxServiceProperties;;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * To inject application specific health checks
 * into the Spring Boot health management 
 */
public class FxServiceHealth implements HealthIndicator {


    @Autowired
    private FxServiceProperties configuration;

    @Override
    public Health health() {
        return Health.up().withDetail("details", "{ 'internals' : 'getting close to limit', 'profile' : '" + this.configuration.getName() + "' }").status("itsok!").build();
    }


}
