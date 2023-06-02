package ru.kpfu.itis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kpfu.itis.security.oAuth.VkOAuthAuthenticationFilter;

@Order(1)
@Configuration
@RequiredArgsConstructor
public class OAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String AUTH_VK_SUCCESS = "/auth/vk/success";
    public static final String AUTH_SUCCESS_REDIRECT = "/profile";

    @Qualifier("userDetailsVkOAuthService")
    private final UserDetailsService userDetailsVkOAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        VkOAuthAuthenticationFilter authFilter = new VkOAuthAuthenticationFilter(userDetailsVkOAuthService);
        authFilter.setFilterProcessesUrl(AUTH_VK_SUCCESS);

        http
                .antMatcher("/auth/**")
                .addFilter(authFilter);
    }
}
