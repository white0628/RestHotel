package com.white.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String userName;
    private String password;
    private String email;
    private Integer sex;
    private String phone;
}
