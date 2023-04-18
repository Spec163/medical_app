package edu.tltsu.medical_app.medical_app.config.jwt;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import edu.tltsu.medical_app.medical_app.entities.Account;
import edu.tltsu.medical_app.medical_app.repositories.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtProvider {

  @Value("${jwt.secret}")
  private String jwtSecret;

  private final AccountRepository accountRepository;

  public JwtProvider(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }


  // сохранять информацию через claims
  public String generateToken(final String login) {
    final Date date = Date.from(LocalDate.now().plusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant());
    final Map<String, Object> claims = new HashMap<>();
    final Account account = this.accountRepository.findAccountByLogin(login);
    claims.put("login", account.getLogin()); // TODO: (Security 1) put accountId
    claims.put("accountId", account.getAccountId()); // TODO: (Security 1) put accountId
    final String result = Jwts.builder()
        .setSubject(login)
        .setExpiration(date)
        .setIssuedAt(new Date())
        .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
        .addClaims(claims)
        .compact();

    return result;
  }

  boolean validateToken(final String token) {
    try {
      Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
      return true;
    } catch (final Exception e) {
      log.error("Invalid token");
    }
    return false;
  }

  String getLoginFromToken(final String token) {
    final Claims claims = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}
