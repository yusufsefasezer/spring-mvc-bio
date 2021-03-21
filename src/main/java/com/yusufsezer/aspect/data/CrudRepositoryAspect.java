package com.yusufsezer.aspect.data;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrudRepositoryAspect {

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.save(*))")
    void save() {
    }

    @Before("save()")
    void saveBefore(JoinPoint joinPoint) {
        logBefore(joinPoint);
    }

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.deleteById(*))")
    void deleteById() {
    }

    @Before("deleteById()")
    void deleteByIdBefore(JoinPoint joinPoint) {
        logBefore(joinPoint);
    }

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.delete(*))")
    void delete() {
    }

    @Before("delete()")
    void deleteBefore(JoinPoint joinPoint) {
        logBefore(joinPoint);
    }

    private void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();

        String output = String.format("%s - %s",
                signature.getName(),
                Arrays.deepToString(args));

        LoggerFactory
                .getLogger(signature.getDeclaringType())
                .info(output);
    }

}
