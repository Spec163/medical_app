package edu.tltsu.medical_app.medical_app.utils;

import java.util.Map;
import edu.tltsu.medical_app.medical_app.entities.Employee;
import edu.tltsu.medical_app.medical_app.exceptions.AccessException;
import edu.tltsu.medical_app.medical_app.exceptions.AccountException;
import edu.tltsu.medical_app.medical_app.repositories.AccountRepository;
import edu.tltsu.medical_app.medical_app.repositories.EmployeeRepository;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
public class TokenParserUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  private final EmployeeRepository employeeRepository;
  private final AccountRepository accountRepository;

  public TokenParserUtils(
      final EmployeeRepository employeeRepository,
      final AccountRepository accountRepository
  ) {
    this.employeeRepository = employeeRepository;
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

  public Employee getEmployeesByToken(final HttpServletRequest request) throws Exception {
    final Object accountIdFromToken =
        this.getClaimsFromToken(this.getTokenFromRequest(request)).get("accountId");
    if (accountIdFromToken == null) {
      throw new AccessException(request);
    }
    final Employee employee =
        this.employeeRepository.findEmployeeByAccountId(Long.parseLong(accountIdFromToken.toString()));

    if (employee == null) {
      throw new AccessException(request);
    }
    return employee;
  }

  public boolean isAdmin(final Long accountId) {
    return Constants.ROLE_ADMIN.equals(this.accountRepository.findById(accountId)
        .orElseThrow(() -> new AccountException(accountId)).getRoleEntity().getName());
  }
}
