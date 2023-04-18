package edu.tltsu.medical_app.medical_app.services;

import edu.tltsu.medical_app.medical_app.config.jwt.JwtProvider;
import edu.tltsu.medical_app.medical_app.dto.auth.AccountDTO;
import edu.tltsu.medical_app.medical_app.dto.auth.AuthResponse;
import edu.tltsu.medical_app.medical_app.entities.Account;
import edu.tltsu.medical_app.medical_app.entities.RoleEntity;
import edu.tltsu.medical_app.medical_app.repositories.AccountRepository;
import edu.tltsu.medical_app.medical_app.repositories.RoleEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
// TODO: refactor (old code)
public class AccountService {

  private final AccountRepository accountRepository;
  private final RoleEntityRepository roleEntityRepository;
  private final JwtProvider jwtProvider;
  private final PasswordEncoder passwordEncoder;

  public AccountService(
      final AccountRepository accountRepository,
      final RoleEntityRepository roleEntityRepository,
      final JwtProvider jwtProvider,
      final PasswordEncoder passwordEncoder
  ) {
    this.accountRepository = accountRepository;
    this.roleEntityRepository = roleEntityRepository;
    this.jwtProvider = jwtProvider;
    this.passwordEncoder = passwordEncoder;
  }

  @Deprecated
  public Account saveUser(final AccountDTO accountDTO) {
    final RoleEntity role = this.roleEntityRepository.findRoleEntityByName("ROLE_USER");
    final Account account = Account
        .builder()
        .roleEntity(role)
        .login(accountDTO.getLogin())
        .password(this.passwordEncoder.encode(accountDTO.getPassword()))
        .build();
    return this.accountRepository.save(account);
  }

  public AuthResponse userAuth(final AccountDTO request) {
    final Account account =
        this.findByLoginAndPassword(request.getLogin(), request.getPassword());

    final String token = this.jwtProvider.generateToken(account.getLogin());

    return new AuthResponse(token, account.getRoleEntity().getName());
  }

  public Account findByLogin(final String login) {
    return this.accountRepository.findAccountByLogin(login);
  }

  public Account findByLoginAndPassword(final String login, final String password) {
    final Account account = this.findByLogin(login);
    if (account != null) {
      if (this.passwordEncoder.matches(password, account.getPassword())) {
        return account;
      }
    }
    return null;
  }
}
