package zhegalov.course.work.feign;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import zhegalov.course.work.dto.ExpressionDto;
import zhegalov.course.work.dto.GeneratorSetup;

public interface ExpressionService {

    ExpressionDto createExpression(GeneratorSetup generatorSetup);

    void setOAuth2AuthorizedClient(OAuth2AuthorizedClient authorizedClient);

}
