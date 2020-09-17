package com.epam.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TrackAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.epam.utils.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        logger.info("Total time for {} is {}", joinPoint, totalTime);
        return proceed;
    }
}