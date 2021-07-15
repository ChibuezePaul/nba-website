package com.isoft.nbawebsite.user;

import com.isoft.nbawebsite.constants.AccountStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailOrSCNumberIgnoreCase(String email, String SCNumber);
    Optional<User> findBySCNumber(String SCNumber);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findByFirstNameOrLastName(String firstName, String lastName);
    List<User> findAllByAccountStatus(AccountStatus accountStatus);
    List<User> findTop3ByOrderByDateCreatedDesc();
}
