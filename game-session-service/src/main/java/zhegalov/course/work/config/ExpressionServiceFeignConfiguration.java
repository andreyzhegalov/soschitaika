package zhegalov.course.work.config;

import java.util.Arrays;

import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import feign.Logger;
import feign.RequestInterceptor;

@SuppressWarnings("deprecation")
public class ExpressionServiceFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    private OAuth2ProtectedResourceDetails resource() {
        final var details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri("http://auth-server:9000/oauth2/token" );
        details.setClientId("messaging-client");
        details.setClientSecret("secret");
        details.setScope(Arrays.asList("message.read", "message.write"));
        return details;
    }

}
