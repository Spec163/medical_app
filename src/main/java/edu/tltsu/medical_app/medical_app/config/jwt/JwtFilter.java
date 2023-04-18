package edu.tltsu.medical_app.medical_app.config.jwt;

import java.io.IOException;
import edu.tltsu.medical_app.medical_app.config.UserDetailsImpl;
import edu.tltsu.medical_app.medical_app.config.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import static org.springframework.util.StringUtils.hasText;

@Component
@Slf4j
public class JwtFilter extends GenericFilterBean {

  private static final String AUTHORIZATION = "Authorization";

  private final JwtProvider jwtProvider;
  private final UserDetailsServiceImpl userDetailsService;

  public JwtFilter(JwtProvider jwtProvider, UserDetailsServiceImpl userDetailsService) {
    this.jwtProvider = jwtProvider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public void doFilter(
      final ServletRequest servletRequest,
      final ServletResponse servletResponse,
      final FilterChain filterChain
  ) throws IOException, ServletException {
    this.logger.info("do filter...");
    final String token = this.getTokenFromRequest((HttpServletRequest) servletRequest);
    log.info("Token: {}", token);
    if (token != null && this.jwtProvider.validateToken(token)) {
      final String userLogin = this.jwtProvider.getLoginFromToken(token);
      final UserDetailsImpl customUserDetails = this.userDetailsService.loadUserByUsername(userLogin);
      final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          customUserDetails, null, customUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(servletRequest, servletResponse);
    log.info("After checking...");
  }

  // private
  public String getTokenFromRequest(final HttpServletRequest request) {
    final String bearer = request.getHeader(AUTHORIZATION);
    if (hasText(bearer) && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }
}
