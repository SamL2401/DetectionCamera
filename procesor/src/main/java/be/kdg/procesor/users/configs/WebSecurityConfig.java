package be.kdg.procesor.users.configs;

import be.kdg.procesor.users.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig //extends WebSecurityConfigurerAdapter
{
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
////        UserDetails user =
////             User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("password")
////                .roles("USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//
//    @Bean
//    public DaoAuthenticationProvider authProvider(
//            UserService userService) {
//        DaoAuthenticationProvider authProvider
//                = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
////authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
}