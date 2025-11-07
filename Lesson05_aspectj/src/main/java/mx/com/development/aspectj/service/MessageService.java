package mx.com.development.aspectj.service;

import org.springframework.stereotype.Service;

/**
 * @author josesaidolanogarcia
 */
@Service
public class MessageService {

    public void sendMessage(String message) {
        System.out.println("sending message: " + message);
    }

    public void receiveMessage(String message) {
        System.out.println("receiving message: " + message);
    }

}
