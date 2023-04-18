package edu.tltsu.medical_app.medical_app.utils;

import java.util.Map;
import edu.tltsu.medical_app.medical_app.entities.UserEntity;
import edu.tltsu.medical_app.medical_app.exceptions.AccessException;
import edu.tltsu.medical_app.medical_app.exceptions.AccountException;
import edu.tltsu.medical_app.medical_app.repositories.AccountRepository;
import edu.tltsu.medical_app.medical_app.repositories.UserEntityRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import static org.springframework.util.StringUtils.hasText;

@Service
public class TokenParserUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  private final UserEntityRepository userEntityRepository;
  private final AccountRepository accountRepository;

  public TokenParserUtils(
      final UserEntityRepository userEntityRepository,
      final AccountRepository accountRepository
  ) {
    this.userEntityRepository = userEntityRepository;
    this.accountRepository = accountRepository;
  }

  private Map<String, Object> getClaimsFromToken(final String token) {
    return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
  }

  public String getTokenFromRequest(final HttpServletRequest request) {
    final String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (hasText(bearer) && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

  public UserEntity getUsersByToken(final HttpServletRequest request) throws Exception {
    final Object accountIdFromToken =
        this.getClaimsFromToken(this.getTokenFromRequest(request)).get("accountId");
    if (accountIdFromToken == null) {
      throw new AccessException(request);
    }
    final UserEntity user =
        this.userEntityRepository.findUserEntityByAccountId(Long.parseLong(accountIdFromToken.toString()));

    return user;
  }

  public boolean isAdmin(final Long accountId) {
    return Constants.ROLE_ADMIN.equals(this.accountRepository.findById(accountId)
        .orElseThrow(() -> new AccountException(accountId)).getRoleEntity().getName());
  }
}
