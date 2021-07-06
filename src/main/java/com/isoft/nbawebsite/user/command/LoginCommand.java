package com.isoft.nbawebsite.user.command;

import lombok.Data;

@Data
public class LoginCommand {
    private String email;
    private String password;
}
