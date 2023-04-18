package edu.tltsu.medical_app.medical_app.config;

import edu.tltsu.medical_app.medical_app.config.jwt.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

//  @Override
//  protected void configure(final HttpSecurity http) throws Exception {
//    http
//        .httpBasic().disable()
//        .csrf().disable()
//        //                .cors().disable()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests()
//        .antMatchers("/admin/*").hasRole("ADMIN")
//        .antMatchers("/user/*").hasAnyRole("ADMIN", "USER")
//        .antMatchers("/register", "/auth").permitAll()
//        .and()
//        .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        //                .cors().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/registration", "/auth").permitAll()
//            .requestMatchers("/api/v1/admin/*").hasRole("ADMIN") // TODO: check permission collision
            .requestMatchers("/api/v1/**").hasAnyRole("USER"))
        .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);

    log.info("Check config...");
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
