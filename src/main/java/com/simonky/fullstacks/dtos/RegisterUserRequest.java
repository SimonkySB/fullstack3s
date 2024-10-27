package com.simonky.fullstacks.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterUserRequest {
    @NotNull(message = "El nombre es requerido")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 a 100 caracteres")
    private String fullname;

    @NotNull(message = "El email es requerido")
    @Email(message = "Email invalido")
    private String email;

    
    @NotNull(message = "La contraseña es requerida")
    @Size(min = 4, max = 16, message = "La contraseña debe tener entre 4 a 16 caracteres")
    private String password;
    
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
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
