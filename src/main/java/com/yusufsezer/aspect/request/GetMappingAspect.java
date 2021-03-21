package com.yusufsezer.aspect.request;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
public class GetMappingAspect {

//    @Before("@annotation(getMapping)")
//    public void logBefore(
//            JoinPoint joinPoint,
//            GetMapping getMapping) {
//
//        Object[] args = joinPoint.getArgs();
//        Signature signature = joinPoint.getSignature();
//
//        String output = String.format("%s - %s",
//                signature.getName(),
//                Arrays.deepToString(args));
//
//        LoggerFactory
//                .getLogger(signature.getDeclaringType())
//                .info(output);
//    }

}
