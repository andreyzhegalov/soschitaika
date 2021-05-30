package zhegalov.course.work.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.SecurityFilterChain;

import feign.RequestInterceptor;

import java.util.Objects;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers("/**.html", "/**.js", "/webjars/**", "/**.css", "/endpoint/**").permitAll().anyRequest()
                .authenticated())
                // Configures authentication support using an OAuth 2.0 and/or OpenID Connect 1.0 Provider.
                // The "authentication flow" is implemented using the Authorization Code Grant,
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/session-client-oidc"))
                .oauth2Client(withDefaults())
                .csrf().disable();
        return http.build();
    }

    @Bean
    OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
                                                          OAuth2AuthorizedClientRepository authorizedClientRepository) {
        // @formatter:off
        final var authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .build();
        // @formatter:on
        final var authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    @Bean
    public RequestInterceptor requestInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {

        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId("session-client-oidc").principal(authentication).build();
            OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
            OAuth2AccessToken accessToken = Objects.requireNonNull(authorizedClient).getAccessToken();

            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getTokenValue());
        };
    }

}
