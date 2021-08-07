package com.isoft.nbawebsite.user;

import com.isoft.nbawebsite.constants.AccountStatus;
import com.isoft.nbawebsite.user.command.ModifyUserCmd;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import com.isoft.nbawebsite.user.command.NameSearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    User signupAdmin(NewUserCmd newUserCmd);
    User signup(NewUserCmd newUserCmd);
    User updateUser(ModifyUserCmd updateCmd);
    User findById(String id);
    List<User> findByFirstNameOrLastName(NameSearchCmd nameSearchCmd);
    User findByEmail(String email);
    User findBySCNumber(String SCNumber);
    User findByPhoneNumber(String phoneNumber);
    List<User> findAllUser();
    Page<User> findAllUser(Pageable pageable);
    void approveNewUserRequest(String id);
    void rejectNewUserRequest(String id);
    void suspendUser(String id, String suspensionPeriod);
    void reinstateUser(String id);
    List<User> findUsersByAccountStatus(AccountStatus accountStatus);
    List<User> findRecentUsers();
    void forgotPassword(String uniqueId);
    void resetPassword(String uniqueId, String password, String confirmPassword);
}
