package org.faketri;

import org.faketri.entity.user.model.Users;
import org.faketri.infastructure.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    //@Bean
    CommandLineRunner cmr(UserService userService) {
        return args -> {
            Users user = new Users();

            user.setEmail("faketri@mail.ru");
            user.setUsername("faketri");
            userService.save(user).subscribe();
        };
    }
}
