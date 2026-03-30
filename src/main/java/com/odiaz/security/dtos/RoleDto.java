package com.odiaz.security.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {


    private Integer id;


    private String name;
}
