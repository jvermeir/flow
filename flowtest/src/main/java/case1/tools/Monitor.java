package case1.tools;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Monitor {

    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    @Before("execution(* case1.controller.*Controller.*(..))")
    public void logControllerStart(JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {
            log(joinPoint, "calling", "controller");
        }
    }

    @AfterReturning("execution(* case1.controller.*Controller.*(..))")
    public void logControllerEnd(JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {
            log(joinPoint, "returning", "controller");
        }
    }

    @Before("execution(* case1.db.*Repository.*(..))")
    public void logDBAccessEntry(JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {
            log(joinPoint, "calling", "repository");
        }
    }

    @AfterReturning("execution(* case1.db.*Repository.*(..))")
    public void logDBAccessExit(JoinPoint joinPoint) {
        if (logger.isDebugEnabled()) {
            log(joinPoint, "returning", "repository");
        }
    }

    private void log(JoinPoint joinPoint, String phase, String type) {
        logger.debug("{\"timestamp\": \"{}\",\"calling\": \"{}\", \"Thread\": \"{}\", \"type\": \"{}\", \"phase\": \"{}\"}", System.currentTimeMillis(), joinPoint, Thread.currentThread(), type, phase);
    }
}
