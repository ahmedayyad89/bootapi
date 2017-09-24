package com.project.mini.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Aspect
@Service("aspectService")
public class LoggingAspect {

//    private static final Logger logger = LoggerFactory
//            .getLogger(LoggingAspect.class);
//
//
//
//    @Before("execution(* com.OLEProjects.mini.*.*.*(..))")
//    public void printSignature(JoinPoint jp){
//        logger.info(jp.getSignature().toLongString());
//        Arrays.stream(jp.getArgs()).forEach(obj -> logger.debug(obj.getClass().toString()));
//    }
//
//
//    @AfterThrowing(pointcut = "execution(* com.OLEProjects.mini.*.*.*(..))", throwing = "exception")
//    public void interceptor(Exception exception)
//    {
//        logger.error("intercepted "+exception.getCause().getMessage());
//    }
//
//
//    @AfterReturning(value = "execution(* com.OLEProjects.mini.*.*.*(..))", returning = "result")
//    public void afterReturning(JoinPoint jp, Object result)
//    {
//        if(result == null)
//        {
//            logger.warn(jp.getSignature().toLongString().concat("Returned null"));
//        }
//    }
//



}
