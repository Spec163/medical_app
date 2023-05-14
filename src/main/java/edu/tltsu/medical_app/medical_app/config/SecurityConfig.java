package edu.tltsu.medical_app.medical_app.config;

import edu.tltsu.medical_app.medical_app.config.jwt.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtFilter jwtFilter;

  @Autowired
  public SecurityConfig(final JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .httpBasic().disable()
        .csrf().disable()
        //                .cors().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/registration", "/auth").permitAll()
            .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN")
            .requestMatchers("/api/v1/public/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
        )
        .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web) -> web
////        .debug(true) // todo: move true to property variable (like https://github.com/eugenp/tutorials/blob/master/spring-security-modules/spring-security-web-boot-4/src/main/java/com/baeldung/securityfilterchain/configuration/SecurityConfig.java)
//        .ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html");
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
