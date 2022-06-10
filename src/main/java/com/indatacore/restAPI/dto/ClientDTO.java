package com.indatacore.restAPI.dto;

import com.indatacore.restAPI.enumeration.EnumSex;
import lombok.*;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientDTO {

    public ClientDTO(String email, String phone, String firstName, String lastName) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private long id;

    private String email;

    @Pattern(regexp = "(\\+212|1)(\\d){9}")
    private String phone;

    private String firstName;

    private String lastName;

    private EnumSex sex;

    private Boolean isActive;

}
