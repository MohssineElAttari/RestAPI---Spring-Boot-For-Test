package com.indatacore.restAPI.models;


import com.indatacore.restAPI.enumeration.EnumSex;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message="Please enter your First Name")
    @Size(max = 40)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    @NotBlank(message="Please enter your Last Name")
    @Size(max = 40)
    private String lastName;

    @Email
    @Column(name = "email",nullable = false, unique = false )
    @NotBlank(message="please enter your Email Address")
    private String email;

    @Enumerated(EnumType.STRING)
    private EnumSex sex;

    @Column(name = "is_active",columnDefinition = "boolean default false")
    private Boolean isActive;
}
