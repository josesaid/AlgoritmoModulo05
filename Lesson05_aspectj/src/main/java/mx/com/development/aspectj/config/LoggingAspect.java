package mx.com.development.aspectj.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* mx.com.development.aspectj..*(..))")
    public void pointcutInsideAspectjPackage(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("a Executing method inside aspectj package: " + className + "." + methodName);
    }

    @Before("execution(* mx.com.development.aspectj.service..*(..))")
    public void pointcutInsideServicePackage(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("b Executing method inside service package: " + className + "." + methodName);
    }

    @Before("execution(* mx.com.development.aspectj..*(..)) && !execution(* mx.com.development.aspectj.repository..*(..))")
    public void pointcutWithoutSubPackageRepository(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("c Executing method without sub-package repository: " + className + "." + methodName);
    }

}