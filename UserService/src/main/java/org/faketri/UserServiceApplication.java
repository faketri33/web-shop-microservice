package org.faketri;

import org.faketri.entity.user.model.User;
import org.faketri.infastructure.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    //@Bean
    CommandLineRunner cmr(UserService userService) {
        return args -> {
            User user = new User();

            user.setEmail("faketri@mail.ru");
            user.setUsername("faketri");
            user.getLikedProduct().add(UUID.fromString("13f5ae89-cc21-4f65-a1e1-97c38e640b6a"));
            userService.save(user).subscribe();
        };
    }
}
