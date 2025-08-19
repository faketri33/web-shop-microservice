package org.faketri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);
    }

    // @Bean
    // CommandLineRunner cmr(UserService userService) {
    //     return args -> {
    //         Profile user = new Profile();

    //         user.setEmail("faketri@mail.ru");
    //         user.setUsername("faketri");
    //         userService.save(user).subscribe();
    //     };
    // }
}
