package mx.com.development.aspectj.service;

import org.springframework.stereotype.Service;

/**
 * @author josesaidolanogarcia
 */
@Service
public class UserService {

    public void createUser(String name, int age) {
        System.out.println("1.- Request to create user: " + name + " | age: " + age);
    }

    public void deleteUser(String name) {
        System.out.println("2.- Request to delete user: " + name);
    }

}