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
public class ExpressionServiceProxyImpl implements ExpressionServiceProxy{
  	private final WebClient webClient;

    @Value("${messages.base-uri}")
	private String messagesBaseUri;

    private OAuth2AuthorizedClient authorizedClient;

	@Override
	public ExpressionDto createExpression(GeneratorSetup generatorSetup) {
		final var expression = this.webClient
				.post()
				.uri(messagesBaseUri)
				.attributes(oauth2AuthorizedClient(authorizedClient))
                .body(Mono.just(generatorSetup), GeneratorSetup.class)
				.retrieve()
				.bodyToMono(ExpressionDto.class)
				.block();
        return expression;
	}

	@Override
	public void setOAuth2AuthorizedClient(OAuth2AuthorizedClient authorizedClient) {
        this.authorizedClient = authorizedClient;
	}
}

