package com.security.role_based_inmemory.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails arjun = User.builder()
                .username("Arjun")
                .password("{noop}Jersey")
                .roles("EMPLOYEE")
                .build();
        UserDetails nani = User.builder()
                .username("Nani")
                .password("{noop}Eega")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails surya = User.builder()
                .username("Surya")
                .password("{noop}Naveen")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(arjun,nani,surya);
    }

    @Bean
    public SecurityFilterChain getAuthorization(HttpSecurity http) throws Exception{
        http.csrf(csrf -> {
            csrf.disable();
        });
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employee").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employee/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employee").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        return http.build();


    }

}
