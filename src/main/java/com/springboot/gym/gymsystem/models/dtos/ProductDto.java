package com.springboot.gym.gymsystem.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(min = 3, message = "El nombre del producto debe contener al menos 3 caracteres")
    private String name;

    @NotNull(message = "Debes poner un precio al producto")
    @Positive (message = "El precio del producto debe ser un valor positivo")
    private float price;


}
