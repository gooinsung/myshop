package com.shop.myshop.aspect;

import com.shop.myshop.data.entity.SlowMethod;
import com.shop.myshop.data.repository.SlowMethodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MethodExecutionAspect {

    private final SlowMethodRepository slowMethodRepository;

    @Around("execution(* com.shop.myshop.api..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();

            long executionTime = end - start;

            if (executionTime > 1000L) {
                log.info("{} - {} takes {} second", joinPoint.getClass(), joinPoint.getSignature().getName(), end - start);
                slowMethodRepository.save(SlowMethod
                        .builder()
                        .className(joinPoint.getSignature().getDeclaringTypeName())
                        .methodName(joinPoint.getSignature().getName())
                        .executionTime(executionTime)
                        .build()
                );
            }


        }
    }

}
