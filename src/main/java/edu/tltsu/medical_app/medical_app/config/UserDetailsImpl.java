package edu.tltsu.medical_app.medical_app.config;

import java.util.Collection;
import java.util.Collections;
import edu.tltsu.medical_app.medical_app.entities.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class UserDetailsImpl implements UserDetails {

  private String login;
  private String password;
  private Collection<? extends GrantedAuthority> grantedAuthorities;

  static UserDetailsImpl fromEmployeeToCustomUserDetails(final Account account) {
    final UserDetailsImpl customUserDetails = new UserDetailsImpl();
    customUserDetails.login = account.getLogin();
    customUserDetails.password = account.getPassword();
    customUserDetails.grantedAuthorities = Collections
        .singletonList(new SimpleGrantedAuthority(account.getRoleEntity().getName()));
    return customUserDetails;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}