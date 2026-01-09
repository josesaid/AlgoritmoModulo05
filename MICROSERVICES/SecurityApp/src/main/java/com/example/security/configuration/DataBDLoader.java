package com.example.security.configuration;

import com.example.security.entities.Authority;
import com.example.security.entities.UserDetails;
import com.example.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * @author josesaidolanogarcia
 */
@Configuration
public class DataBDLoader {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return a -> {

            UserDetails said = new UserDetails();
            said.setUsername("said");
            said.setPassword(passwordEncoder.encode("said"));
            said.setEnabled(true);
            said.setAuthorities(
                    Set.of(
                            new Authority(null, "said", "ROLE_ADMIN"),
                            new Authority(null, "said", "ROLE_USER"),
                            new Authority(null, "said", "ROLE_CUSTOMER")
                    )
            );

            UserDetails admin  = new UserDetails();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);
            admin.setAuthorities(
                    Set.of(
                            new Authority(null, "admin", "ROLE_ADMIN")
                    )
            );

            UserDetails user = new UserDetails();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEnabled(true);
            user.setAuthorities(
                    Set.of(
                            new Authority(null, "user", "ROLE_USER")
                    )
            );

            UserDetails customer = new UserDetails();
            customer.setUsername("customer");
            customer.setPassword(passwordEncoder.encode("customer"));
            customer.setEnabled(true);
            customer.setAuthorities(
                    Set.of(
                            new Authority(null, "customer", "ROLE_CUSTOMER")
                    )
            );

            userRepository.saveAll(Set.of(said, admin, user));

            System.out.println("Data loaded");

        };

    }

}
