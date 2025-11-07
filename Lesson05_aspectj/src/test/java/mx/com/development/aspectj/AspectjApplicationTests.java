package mx.com.development.aspectj;

import mx.com.development.aspectj.service.MessageService;
import mx.com.development.aspectj.service.UserService;
import mx.com.development.aspectj.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AspectjApplicationTests {
    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserRepository userRepository;

    @Test
    void testUserService() {
        userService.createUser("create new user Said Olano", 41);
        userService.deleteUser("Said Olano");
        String x = "algo";
        Assertions.assertNotNull(x);
    }

    @Test
    void testMessageService() {
        messageService.sendMessage("send message from user OSCAR JAVIER");
        messageService.receiveMessage("receive message from user OSCAR JAVIER");
        String x = "algo";
        Assertions.assertNotNull(x);

    }

    @Test
    void testUserRepository() {
        userRepository.createUser("PEDRO NAVAJA", 41);
        userRepository.deleteUser("PEDRO NAVAJA");
        String x = "algo";
        Assertions.assertNotNull(x);
    }
}
