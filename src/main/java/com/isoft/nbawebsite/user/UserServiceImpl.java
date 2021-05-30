package com.isoft.nbawebsite.user;

import com.isoft.nbawebsite.exception.CustomException;
import com.isoft.nbawebsite.user.command.ModifyUserCmd;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import com.isoft.nbawebsite.user.command.SearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final CustomException USER_NOT_FOUND = new CustomException("User Not Found");
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signup(NewUserCmd newUserCmd) {
        User user = new User();
        user.setAddress(newUserCmd.getAddress());
        user.setChamberAddress(newUserCmd.getChamberAddress());
        user.setCourtNumber(newUserCmd.getCourtNumber());
        user.setEmail(newUserCmd.getEmail());
        user.setPhoneNumber(newUserCmd.getPhoneNumber());
        user.setFirstName(newUserCmd.getFirstName());
        user.setLastName(newUserCmd.getLastName());
        user.setPassword(passwordEncoder.encode(newUserCmd.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(ModifyUserCmd updateCmd) {
        User user = findById(updateCmd.getId());
        user.setEmail(updateCmd.getEmail());
        user.setPhoneNumber(updateCmd.getPhoneNumber());
        user.setFirstName(updateCmd.getFirstName());
        user.setLastName(updateCmd.getLastName());
        user.setAddress(updateCmd.getAddress());
        user.setChamberAddress(updateCmd.getChamberAddress());
        user.setCourtNumber(updateCmd.getCourtNumber());
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public List<User> findByFirstNameOrLastName(SearchCmd nameSearchCmd) {
        return userRepository.findByFirstNameOrLastName(nameSearchCmd.getFirstName(), nameSearchCmd.getLastName());
    }

    @Override
    public User findByEmail(SearchCmd emailSearchCmd) {
        return userRepository.findByEmail(emailSearchCmd.getEmail()).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public User findByPhoneNumber(SearchCmd phoneSearchCmd) {
        return userRepository.findByPhoneNumber(phoneSearchCmd.getPhoneNumber()).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
