package com.isoft.nbawebsite.user.command;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class NewUserCmd {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    private String address;
    private String courtNumber;
    private String chamberAddress;
    private String password;
}
