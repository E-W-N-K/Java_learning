package org.carproject.pet_project_1;

import org.carproject.pet_project_1.model.User;
import org.carproject.pet_project_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class PetProject1Application implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PetProject1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Создаем админа при запуске, если его ещё нет
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User(
                    "admin",
                    "sakunevgeniy56@gmail.com",
                    passwordEncoder.encode("admin"), // Хэшируем пароль
                    "ADMIN"
            );
            userRepository.save(admin);
            System.out.println("Admin user created: admin / admin");
        }
    }

}
