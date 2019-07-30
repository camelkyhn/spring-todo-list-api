package com.camelkyhn.springtodolistapi.middleware.configurations;

import com.camelkyhn.springtodolistapi.middleware.entities.CustomUserDetails;
import com.camelkyhn.springtodolistapi.middleware.entities.Role;
import com.camelkyhn.springtodolistapi.middleware.entities.User;
import com.camelkyhn.springtodolistapi.service.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    @Bean(name= BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, IUserRepository userRepository) throws Exception
    {
        if (userRepository.count() <= 0)
        {
            User user = new User("Admin", "User", "admin@gmail.com", "admin123"); // Can be get from an env file or env variable.
            user.setRoles(Arrays.asList(new Role("ADMIN")));
            userRepository.save(user);
        }

        builder.userDetailsService(username -> new CustomUserDetails(userRepository.findByUsername(username)))
                .passwordEncoder(new PasswordEncoder()
                {
                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword)
                    {
                        return rawPassword.equals(encodedPassword);
                    }

                    @Override
                    public String encode(CharSequence rawPassword)
                    {
                        return rawPassword.toString();
                    }
                });
    }
}