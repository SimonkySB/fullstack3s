package com.simonky.fullstacks.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "Email invalido")
    private String email;

    @NotNull(message = "La contraseña no puede ser nulo")
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
