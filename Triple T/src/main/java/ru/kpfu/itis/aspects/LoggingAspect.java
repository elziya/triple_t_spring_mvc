package ru.kpfu.itis.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {


    @After("execution(* ru.kpfu.itis.controllers.ExceptionHandlerController.*(..))")
    public void logExecutionTime(JoinPoint joinPoint) {
        String exceptionName = joinPoint.getArgs()[0].getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String currentUser =  SecurityContextHolder.getContext().getAuthentication().getName();

        log.info(exceptionName + " was thrown and method [" + methodName + "] of [" + className + "] handled it, " +
                "current user: [" + currentUser + "]");
    }
}
