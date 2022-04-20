package com.emirsanchez.springsecurity;

import com.emirsanchez.springsecurity.domain.Role;
import com.emirsanchez.springsecurity.domain.User;
import com.emirsanchez.springsecurity.service.IUserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@Slf4j
@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
        log.info("FUNCIONANDO!");
    }

    @Bean
    CommandLineRunner run(IUserService userService) {

        return args -> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_MANAGER"));

            userService.saveUser(new User(null, "Emir Sanchez", "esanchez", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jose Perez", "jperez", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Luis Aliaga", "laliaga", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Marco Polo", "mpolo", "1234", new ArrayList<>()));

            userService.addRoleToUser("esanchez", "ROLE_USER");
            userService.addRoleToUser("jperez", "ROLE_ADMIN");
            userService.addRoleToUser("laliaga", "ROLE_MANAGER");
            userService.addRoleToUser("mpolo", "ROLE_SUPER_MANAGER");

        };

    }

}
