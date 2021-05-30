package com.isoft.nbawebsite.user;

import com.isoft.nbawebsite.user.command.ModifyUserCmd;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import com.isoft.nbawebsite.user.command.SearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    User signup(NewUserCmd newUserCmd);
    User updateUser(ModifyUserCmd updateCmd);
    User findById(String id);
    List<User> findByFirstNameOrLastName(SearchCmd nameSearchCmd);
    User findByEmail(SearchCmd emailSearchCmd);
    User findByPhoneNumber(SearchCmd phoneSearchCmd);
    List<User> findAllUser();
    Page<User> findAllUser(Pageable pageable);
}
