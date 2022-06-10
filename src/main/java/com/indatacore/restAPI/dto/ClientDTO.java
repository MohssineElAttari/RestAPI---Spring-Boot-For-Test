package com.indatacore.restAPI.dto;

import com.indatacore.restAPI.enumeration.EnumSex;
import lombok.*;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ClientDTO {
    private long id;

    private String email;

    @Pattern(regexp = "(\\+212|1)(\\d){9}")
    private String phone;

    private String firstName;

    private String lastName;

    private EnumSex sex;

    private Boolean isActive;

}
