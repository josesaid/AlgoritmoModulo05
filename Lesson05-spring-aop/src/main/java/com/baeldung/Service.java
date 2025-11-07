package com.baeldung;

import org.springframework.stereotype.Component;

@Component
public class Service {

    @LogExecutionTime
    @ValidationBehavior
    public void xValidation() throws InterruptedException {
        Thread.sleep(1* 500);
        System.out.println("aqui se esta llevando a cabo la validacion xValidation".toUpperCase());
    }

    @LogExecutionTime
    @ValidationBehavior
    public void yValidation() throws InterruptedException {
        Thread.sleep(1* 500);
        System.out.println("aqui se esta llevando a cabo la validacion yValidation".toUpperCase());
    }
}
