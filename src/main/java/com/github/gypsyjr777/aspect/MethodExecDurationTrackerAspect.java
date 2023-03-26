package com.github.gypsyjr777.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class MethodExecDurationTrackerAspect {
    private Long durationMillis;

    @Before(value = "execution(public String mainPage())")
    public void beforeDurationTrackingAdvice() {
        durationMillis = new Date().getTime();
        log.info("Duration tracking begins");
    }

    @After(value = "execution(public String mainPage())")
    public void afterDurationTrackingAdvice() {
        durationMillis = new Date().getTime() - durationMillis;
        log.info("Duration tracking end: {} ms", durationMillis);
    }

    @After(value = "within(com.github.gypsyjr777.service.BookService)")
    public void beforeDAdvice(JoinPoint joinPoint) {
        log.info("Author: {}", joinPoint.getArgs());
    }

    @Pointcut(value = "execution(* book*())")
    public void allBookInfoMethods() {
    }

    @After("allBookInfoMethods()")
    public void afterAllBookInfoMethodsAdvice() {
        log.info("Advice to all book info methods");
    }

    @Pointcut(value = "within(com.github.gypsyjr777.service.*)")
    public void allServicesMethods() {
    }

    @After("allServicesMethods()")
    public void afterAllServicesMethodsAdvice(JoinPoint joinPoint) {
        log.info("Advice to all services methods...  {}", joinPoint.toString());
    }
}
