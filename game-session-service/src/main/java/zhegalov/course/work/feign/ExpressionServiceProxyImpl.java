package zhegalov.course.work.feign;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import zhegalov.course.work.controllers.dto.ExpressionDto;
import zhegalov.course.work.controllers.dto.GeneratorSetup;

@Service
@RequiredArgsConstructor
public class ExpressionServiceProxyImpl implements ExpressionServiceProxy {
    private static final String EXPRESSION_URI = "/api/expressions";

    private final WebClient webClient;

    @Value("${expression.base-uri}")
    private String expressionServiceBaseUri;

    private OAuth2AuthorizedClient authorizedClient;

    @Override
    public ExpressionDto createExpression(GeneratorSetup generatorSetup) {
        return this.webClient.post().uri(expressionServiceBaseUri + EXPRESSION_URI)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .body(Mono.just(generatorSetup), GeneratorSetup.class)
                .retrieve()
                .bodyToMono(ExpressionDto.class)
                .block();
    }

    @Override
    public void setOAuth2AuthorizedClient(OAuth2AuthorizedClient authorizedClient) {
        this.authorizedClient = authorizedClient;
    }
}
