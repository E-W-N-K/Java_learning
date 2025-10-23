package org.carproject.pet_project_1.dto;

// DTO для запроса регистрации
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;

    public RegisterRequestDTO() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
