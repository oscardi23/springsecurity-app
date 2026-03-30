package com.odiaz.security.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductItem {

    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private double price;
    @NotBlank
    private String description;


}
