package com.isoft.nbawebsite.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isoft.nbawebsite.commons.data.AbstractEntity;
import com.isoft.nbawebsite.constants.AccountStatus;
import com.isoft.nbawebsite.constants.Role;
import com.isoft.nbawebsite.constants.SuspensionPeriod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class User extends AbstractEntity {
    private String firstName;

    private String lastName;

    @Email
    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String phoneNumber;

    private String address;

    @Indexed(unique = true)
    private String SCNumber;

    private String chamberAddress;

    @JsonIgnore
    private String password;

    private String honorific;

    private String imageUrl;

    private Role role;

    private boolean isSuspended;

    private SuspensionPeriod suspensionPeriod;

    private LocalDateTime suspensionDate;

    private AccountStatus accountStatus = AccountStatus.PENDING;
}
