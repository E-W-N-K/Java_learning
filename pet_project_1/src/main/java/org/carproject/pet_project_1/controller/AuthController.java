package org.carproject.pet_project_1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.carproject.pet_project_1.dto.AuthResponseDTO;
import org.carproject.pet_project_1.dto.LoginRequestDTO;
import org.carproject.pet_project_1.dto.RegisterRequestDTO;
import org.carproject.pet_project_1.model.User;
import org.carproject.pet_project_1.repository.UserRepository;
import org.carproject.pet_project_1.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Регистрация нового пользователя
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        // Проверка на существование username
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        // Проверка на существование email
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        // Создание нового пользователя
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()), // Хэшируем пароль
                "USER" // По умолчанию роль USER
        );

        userRepository.save(user);

        return ResponseEntity.ok(new AuthResponseDTO("User registered successfully", user.getUsername(), user.getRole()));
    }

    // Логин пользователя
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request, HttpServletResponse response) {
        try {
            // Аутентификация через Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Если аутентификация успешна, получаем пользователя
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Генерируем JWT токен
            String jwt = jwtUtil.generateToken(user.getUsername(), user.getRole());

            // Создаем HttpOnly cookie
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);  // ВАЖНО: защита от XSS
            cookie.setSecure(false);   // Установить true для HTTPS в продакшене
            cookie.setPath("/");       // Доступен для всех путей
            cookie.setMaxAge(15 * 60); // 15 минут (как время жизни токена)

            // Добавляем cookie в ответ
            response.addCookie(cookie);

            return ResponseEntity.ok(new AuthResponseDTO("Login successful", user.getUsername(), user.getRole()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // Logout пользователя
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Удаляем cookie, устанавливая MaxAge = 0
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Удаляем cookie

        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful");
    }

    // Проверка текущего статуса аутентификации
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(new AuthResponseDTO("Authenticated", user.getUsername(), user.getRole()));
    }
}
