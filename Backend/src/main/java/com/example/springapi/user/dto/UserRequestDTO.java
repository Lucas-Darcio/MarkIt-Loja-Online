package com.example.springapi.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;


public record UserRequestDTO(
        @NotBlank(message="O nome é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
        String name,

        @NotBlank(message="O email é obrigatório.")
        @Email
        @Size(min = 8, max = 40, message = "O email deve ter entre 8 e 40 caracteres.")
        String email,

        @NotBlank(message="A senha é obrigatória.")
        @Size(min = 8, max = 15, message = "A senha deve ter entre 8 e 15 caracteres.")
        String password

) {}
