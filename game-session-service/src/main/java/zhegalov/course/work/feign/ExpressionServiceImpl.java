package zhegalov.course.work.feign;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import zhegalov.course.work.dto.ExpressionDto;
import zhegalov.course.work.dto.GeneratorSetup;

@Service
@RequiredArgsConstructor
public class ExpressionServiceImpl implements ExpressionService {
    private static final String EXPRESSION_URI = "/api/expressions";

    private final WebClient webClient;

    @Value("${expression.base-uri}")
    private String expressionServiceBaseUri;

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public ExpressionDto createExpression(GeneratorSetup generatorSetup) {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("expression-client-oidc")
                                                                        .principal(authentication)
                                                                        .build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

        return this.webClient.post().uri(expressionServiceBaseUri + EXPRESSION_URI)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .body(Mono.just(generatorSetup), GeneratorSetup.class)
                .retrieve()
                .bodyToMono(ExpressionDto.class)
                .block();
    }
}
