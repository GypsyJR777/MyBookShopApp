package com.github.gypsyjr777.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExceptionAspect {
    @Pointcut("within(com.github.gypsyjr777.*)")
    public void catchAllExceptionsPointCut() {}

    @AfterThrowing(value = "catchAllExceptionsPointCut()", throwing = "ex")
    public void catchAllExceptionsAdvice(Throwable ex) {
        log.error(ex.toString());
    }
}
