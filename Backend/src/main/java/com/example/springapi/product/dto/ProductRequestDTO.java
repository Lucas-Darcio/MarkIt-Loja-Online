package com.example.springapi.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
        String name,

        @NotBlank(message = "O nome é obrigatório.")
        @Size(max=500, message="A descrição máxima deve ter no máximo 500 caracteres.")
        String description,

        @NotNull(message="O preço é obrigatório.")
        @Min(value = 0,  message = "O preço não pode ser negativo.")
        BigDecimal price,

        @NotNull(message="O estoque é obrigatório.")
        @Min(value=0, message="O estoque não pode ser negativo.")
        Integer stock


) {
}
