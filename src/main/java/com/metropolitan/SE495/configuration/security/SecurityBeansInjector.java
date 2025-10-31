package com.metropolitan.SE495.configuration.security;

import com.metropolitan.SE495.authentication.UserProvider;
import com.metropolitan.SE495.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityBeansInjector {
    private UserRepository userRepository;
    private UserProvider userProvider;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userProvider.userDetailsService());
        provider.setPasswordEncoder(userProvider.passwordEncoder());

        return provider;
    }
}
