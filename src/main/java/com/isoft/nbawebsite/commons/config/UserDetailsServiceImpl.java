package com.isoft.nbawebsite.commons.config;

import com.isoft.nbawebsite.constants.AccountStatus;
import com.isoft.nbawebsite.constants.SuspensionPeriod;
import com.isoft.nbawebsite.exception.CustomException;
import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException(String.format("User Not Found With Email %s", s)));
		checkAccountStatus(user);
		checkSuspensionStatus(user);
		return new org.springframework.security.core.userdetails.User ( user.getEmail () , user.getPassword () , Collections.singleton (user.getRole()) );
	}

	private void checkSuspensionStatus(User user) {
		if(user.isSuspended()) {
			if (user.getSuspensionPeriod().equals(SuspensionPeriod.ONE_WEEK) && user.getSuspensionDate().plusWeeks(1).isAfter(LocalDateTime.now()))
				throw new CustomException(String.format("User Account Has Been Suspended For %s", user.getSuspensionPeriod().label));
			else if (user.getSuspensionPeriod().equals(SuspensionPeriod.SIX_MONTH) && user.getSuspensionDate().plusMonths(6).isAfter(LocalDateTime.now()))
				throw new CustomException(String.format("User Account Has Been Suspended For %s", user.getSuspensionPeriod().label));
			else if (user.getSuspensionPeriod().equals(SuspensionPeriod.LIFETIME))
				throw new CustomException("User Account Has Been Suspended Indefinitely");
			else {
				user.setSuspended(false);
				user.setSuspensionPeriod(null);
				user.setSuspensionDate(null);
				userRepository.save(user);
			}
		}
	}

	private void checkAccountStatus(User user) {
		if(user.getAccountStatus().equals(AccountStatus.PENDING)) {
			throw new CustomException("User Account Has Not Been Approved");
		}

		if(user.getAccountStatus().equals(AccountStatus.REJECTED)) {
			throw new CustomException("User Account Was Rejected");
		}
	}
}