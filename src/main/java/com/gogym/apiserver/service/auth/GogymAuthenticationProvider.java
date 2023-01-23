package com.gogym.apiserver.service.auth;

import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.exception.LoginException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GogymAuthenticationProvider implements AuthenticationProvider {
    private final GogymUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println("username : " + username + " , password : " + password);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new LoginException("username is not found. username=" + username, ErrorCode.NOT_FOUND_USERPHONE);
        }

        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("password is not matched");
            throw new LoginException("password is not matched", ErrorCode.WRONG_PASSWORD);
        }

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
