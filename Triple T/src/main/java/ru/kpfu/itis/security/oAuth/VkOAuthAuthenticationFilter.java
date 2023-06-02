package ru.kpfu.itis.security.oAuth;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.kpfu.itis.config.OAuthSecurityConfiguration;
import ru.kpfu.itis.exceptions.TripleTVkOAuthException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VkOAuthAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Qualifier("userDetailsVkOAuthService")
    private final UserDetailsService userDetailsVkOAuthService;

    public VkOAuthAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsVkOAuthService = userDetailsService;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String code = request.getParameter("code");

        try {
            UserDetails userDetails = userDetailsVkOAuthService.loadUserByUsername(code);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        catch (TripleTVkOAuthException e){
            request.setAttribute("message", e.getMessage());
            request.setAttribute("status", e.getHttpStatus().value());
            request.getRequestDispatcher("/WEB-INF/jsp/error/error.jsp").forward(request, response);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        response.sendRedirect(OAuthSecurityConfiguration.AUTH_SUCCESS_REDIRECT);
    }
}
