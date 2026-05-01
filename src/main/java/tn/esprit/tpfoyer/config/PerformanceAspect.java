package tn.esprit.tpfoyer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        log.info(">>> START METHOD : " + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();   // Executes real method

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        log.info("<<< END METHOD : "
                + joinPoint.getSignature().getName()
                + " EXECUTION TIME = " + duration + " ms");

        return result;
    }
}
