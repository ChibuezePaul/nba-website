package com.isoft.nbawebsite.user;

import com.isoft.nbawebsite.constants.AccountStatus;
import com.isoft.nbawebsite.constants.Role;
import com.isoft.nbawebsite.constants.SuspensionPeriod;
import com.isoft.nbawebsite.exception.CustomException;
import com.isoft.nbawebsite.user.command.ModifyUserCmd;
import com.isoft.nbawebsite.user.command.NewUserCmd;
import com.isoft.nbawebsite.user.command.NameSearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
        user.setSCNumber(newUserCmd.getSCNumber());
        user.setEmail(newUserCmd.getEmail());
        user.setPhoneNumber(newUserCmd.getPhoneNumber());
        user.setFirstName(newUserCmd.getFirstName());
        user.setLastName(newUserCmd.getLastName());
        user.setPassword(passwordEncoder.encode(newUserCmd.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public User signupAdmin(NewUserCmd newUserCmd) {
        User user = new User();
        user.setAddress(newUserCmd.getAddress());
        user.setChamberAddress(newUserCmd.getChamberAddress());
        user.setSCNumber(newUserCmd.getSCNumber());
        user.setEmail(newUserCmd.getEmail());
        user.setPhoneNumber(newUserCmd.getPhoneNumber());
        user.setFirstName(newUserCmd.getFirstName());
        user.setLastName(newUserCmd.getLastName());
        user.setHonorific(newUserCmd.getHonorific());
        user.setPassword(passwordEncoder.encode(newUserCmd.getPassword()));
        user.setRole(Role.ADMIN);
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
        user.setSCNumber(updateCmd.getSCNumber());
        user.setHonorific(updateCmd.getHonorific());
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public List<User> findByFirstNameOrLastName(NameSearchCmd nameSearchCmd) {
        return userRepository.findByFirstNameOrLastName(nameSearchCmd.getFirstName(), nameSearchCmd.getLastName());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public User findBySCNumber(String SCNumber) {
        return userRepository.findBySCNumber(SCNumber).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void approveNewUserRequest(String id) {
        User user = findById(id);
        checkIfAccountHasBeenReviewed(user);
        user.setAccountStatus(AccountStatus.APPROVED);
        userRepository.save(user);
    }

    @Override
    public void rejectNewUserRequest(String id) {
        User user = findById(id);
        checkIfAccountHasBeenReviewed(user);
        user.setAccountStatus(AccountStatus.REJECTED);
        userRepository.save(user);
    }

    @Override
    public void suspendUser(String id, String suspensionPeriodLabel) {
        User user = findById(id);
        SuspensionPeriod suspensionPeriod = SuspensionPeriod.getSuspensionPeriodByLabel(suspensionPeriodLabel).orElseThrow(() -> new CustomException("Invalid Suspension Period Specified"));
        if(user.isSuspended() && user.getSuspensionPeriod().equals(suspensionPeriod)) {
            throw new CustomException("User Account Already Suspended For The Specified Period");
        }
        user.setSuspended(true);
        user.setSuspensionPeriod(suspensionPeriod);
        user.setSuspensionDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void reinstateUser(String id) {
        User user = findById(id);
        if(!user.isSuspended()) {
            throw new CustomException("User Account Not Suspended");
        }
        user.setSuspended(false);
        user.setSuspensionPeriod(null);
        user.setSuspensionDate(null);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersByAccountStatus(AccountStatus accountStatus) {
        return userRepository.findAllByAccountStatus(accountStatus);
    }

    @Override
    public List<User> findRecentUsers() {
        return userRepository.findTop3ByOrderByDateCreatedDesc();
    }

    @Override
    public void forgotPassword(String uniqueId) {
        userRepository.findByEmailOrSCNumberIgnoreCase(uniqueId, uniqueId).orElseThrow(() -> USER_NOT_FOUND);
    }

    @Override
    public void resetPassword(String uniqueId, String password, String confirmPassword) {
        if(password != null && !password.equals(confirmPassword)) {
            throw new CustomException("Password Mismatch! Kindly Supply Same Password In Both Fields");
        }
        User user = userRepository.findByEmailOrSCNumberIgnoreCase(uniqueId, uniqueId).orElseThrow(() -> USER_NOT_FOUND);
        if(passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException("Password is same as current password");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    private void checkIfAccountHasBeenReviewed(User user) {
        if(!user.getAccountStatus().equals(AccountStatus.PENDING)) {
            throw new CustomException("Account Has Already Been Reviewed");
        }
    }
}
