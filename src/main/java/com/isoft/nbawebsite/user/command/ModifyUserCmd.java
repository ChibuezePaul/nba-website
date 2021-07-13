package com.isoft.nbawebsite.user.command;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ModifyUserCmd {
    @NotBlank
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String SCNumber;
    private String chamberAddress;
    private String honorific;
}
