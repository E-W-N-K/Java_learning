package org.carproject.pet_project_1.repository;

import org.carproject.pet_project_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Поиск по username для аутентификации
    Optional<User> findByUsername(String username);

    // Проверка существования пользователя по username
    Boolean existsByUsername(String username);

    // Проверка существования пользователя по email
    Boolean existsByEmail(String email);
}
