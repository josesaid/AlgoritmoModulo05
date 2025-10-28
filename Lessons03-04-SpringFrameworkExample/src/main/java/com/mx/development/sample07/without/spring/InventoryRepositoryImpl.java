package com.mx.development.sample07.without.spring;

public class InventoryRepositoryImpl implements InventoryRepositoryI {
    @Override
    public void updateDB() {
        System.out.println(">>> Updating inventory");
    }
}
