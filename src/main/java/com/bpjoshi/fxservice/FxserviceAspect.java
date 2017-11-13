package com.bpjoshi.fxservice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Aspect for logging request to controllers
 */
@Aspect
@Component
public class FxserviceAspect {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(public * com.bpjoshi.fxservice.api.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        log.info("::=> Before REST call <=:: " + pjp);
    }
}
