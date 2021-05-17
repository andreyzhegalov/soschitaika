package zhegalov.course.work.feign;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import zhegalov.course.work.controllers.dto.ExpressionDto;
import zhegalov.course.work.controllers.dto.GeneratorSetup;


public interface ExpressionServiceProxy {

	ExpressionDto createExpression(GeneratorSetup generatorSetup, OAuth2AuthorizedClient authorizedClient);

}
