package com.isoft.nbawebsite.commons.config;

import com.isoft.nbawebsite.user.User;
import com.isoft.nbawebsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername (String s ) throws UsernameNotFoundException {
		User user = userRepository.findByCourtNumber ( s )
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User Not Found With Court Number %s", s)));

		return new org.springframework.security.core.userdetails.User ( user.getCourtNumber () , user.getPassword () , Collections.emptyList () );
	}
}