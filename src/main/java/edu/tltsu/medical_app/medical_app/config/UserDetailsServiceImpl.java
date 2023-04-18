package edu.tltsu.medical_app.medical_app.config;

import edu.tltsu.medical_app.medical_app.entities.Account;
import edu.tltsu.medical_app.medical_app.repositories.AccountRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
  private final AccountRepository accountRepository;

  public UserDetailsServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetailsImpl loadUserByUsername(final String username) throws UsernameNotFoundException {
    final Account account = this.accountRepository.findAccountByLogin(username);
    return UserDetailsImpl.fromUserEntityToCustomUserDetails(account);
  }
}