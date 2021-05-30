package com.isoft.nbawebsite.user;

import com.isoft.nbawebsite.commons.data.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;

@EqualsAndHashCode(callSuper = true)
@Data @Document
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
    private String courtNumber;

    private String chamberAddress;

    private String password;
}
