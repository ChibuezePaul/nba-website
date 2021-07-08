package com.isoft.nbawebsite.commons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_LIST = {
        "/assets/**",
        "/css/**",
        "/img/**",
        "/js/**",
    };

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/events").permitAll()
                .antMatchers("/news").permitAll()
                .antMatchers("/president").permitAll()
                .antMatchers("/findmember").permitAll()
                .antMatchers("/catalogue").permitAll()
                .antMatchers("/individualprofile/**").permitAll()
                .antMatchers("/card").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/viewevents").permitAll()
                .antMatchers("/newspage").permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin().permitAll().loginPage("/login")
                .failureHandler((request, response, exception) -> response.sendRedirect("/login?error=true&message="+exception.getMessage()))
				.defaultSuccessUrl("/dashboard")
                .and()
                .logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true);
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_LIST);
    }
}
