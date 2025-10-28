package com.mx.development.sample07.with.spring;

import org.springframework.stereotype.Component;

/**
 * @author josesaidolanogarcia
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepositoryI {
    @Override
    public void updateDB() {
        System.out.println(">>> Updating inventory");
    }
}
