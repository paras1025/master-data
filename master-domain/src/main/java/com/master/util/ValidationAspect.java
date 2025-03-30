package com.master.util;

import com.master.exception.MasterDataException;
import com.master.exception.MastersValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* com.master.*.*(..))")
    public void validateBefore(JoinPoint joinPoint) throws MastersValidationException {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg!=null && arg.getClass().isAnnotationPresent(ValidateRequest.class)) {
                try {
                    MastersValidator.validateMandatoryFields(arg);
                } catch (IllegalAccessException e) {
                    throw new MasterDataException("Error accessing fields for validation", e);
                }
            }
        }
    }
}
