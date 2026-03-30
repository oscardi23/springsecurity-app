package com.odiaz.security.dtos;


import com.odiaz.security.entities.Role;
import com.odiaz.security.enums.RoleList;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Integer id;
    private String username;
    private String password;
    private Boolean active;
    private RoleList role;
}
