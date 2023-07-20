package tn.esprit.spring.Configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    //mbaad l execution mtaa l fonction // taayet lel after returning w after throwing
    @After("execution(* tn.esprit.spring.Servcies.*.ajouter*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        l.info("Execution Reussie!");
    }
}