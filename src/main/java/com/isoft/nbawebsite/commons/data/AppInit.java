package com.isoft.nbawebsite.commons.data;

import com.isoft.nbawebsite.constants.AccountStatus;
import com.isoft.nbawebsite.constants.Role;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserRepository;
import com.isoft.nbawebsite.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class AppInit  implements InitializingBean {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(userRepository.findBySCNumber("0011221").isPresent()) {
            return;
        }
        User user = new User();
        String address = "NBA Official Address";
        user.setAddress(address);
        user.setChamberAddress("001122");
        user.setSCNumber("0011221");
        user.setEmail("su@nba.com");
        user.setPhoneNumber("08100011122");
        user.setFirstName("SU");
        user.setLastName("User");
        user.setPassword(passwordEncoder.encode("su"));
        user.setRole(Role.SU_ADMIN);
        user.setAccountStatus(AccountStatus.APPROVED);

        User user1 = new User();
        user1.setAddress(address);
        user1.setChamberAddress("001133");
        user1.setSCNumber("0011222");
        user1.setEmail("admin@nba.com");
        user1.setPhoneNumber("08100011133");
        user1.setFirstName("Admin");
        user1.setLastName("User");
        user1.setPassword(passwordEncoder.encode("admin"));
        user1.setRole(Role.ADMIN);
        user1.setAccountStatus(AccountStatus.APPROVED);

        User user2 = new User();
        user2.setAddress(address);
        user2.setChamberAddress("001144");
        user2.setSCNumber("0011223");
        user2.setEmail("user@nba.com");
        user2.setPhoneNumber("08100011144");
        user2.setFirstName("Associate");
        user2.setLastName("User");
        user2.setPassword(passwordEncoder.encode("user"));
        user2.setRole(Role.USER);
        user2.setAccountStatus(AccountStatus.APPROVED);
        userRepository.saveAll(Arrays.asList(user, user1, user2));
        log.info("========= {}, {} and {} Created Successfully ==========", user.getEmail(), user1.getEmail(), user2.getEmail());
    }
}
