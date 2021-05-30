package com.isoft.nbawebsite.user.command;

import lombok.Data;

@Data
public class SearchCmd {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}
