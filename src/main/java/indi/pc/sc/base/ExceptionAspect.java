package indi.pc.sc.base;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Aspect
public class ExceptionAspect {

    @AfterThrowing()
    public void afterThrowing(Throwable e) {

    }

}
