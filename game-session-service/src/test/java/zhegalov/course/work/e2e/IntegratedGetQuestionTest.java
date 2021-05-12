package zhegalov.course.work.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.plexus.util.IOUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class, MongoAutoConfiguration.class })
public class IntegratedGetQuestionTest {
    @Configuration
    public static class TestContext {
    }

    @Value("${webserver.gamesession.address}")
    private String gameSessionServerAddress;

    @Test
    void getQuestionScenario() throws Exception {
        final var jsonSettings = "{\"min\":0,\"max\":10,\"valueCnt\":2,\"operations\":[\"SUM\"]}";

        final var httpPostSession = new HttpPost(gameSessionServerAddress + "/api/sessions");
        httpPostSession.setEntity(new StringEntity(jsonSettings));
        httpPostSession.setHeader("Accept", "application/json");
        httpPostSession.setHeader("Content-type", "application/json");

        final var sessionHttpResponse = HttpClientBuilder.create().build().execute(httpPostSession);

        assertThat(sessionHttpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        final var responseSessionDtoString = IOUtil.toString(sessionHttpResponse.getEntity().getContent());
        assertThat(responseSessionDtoString).contains("sessionId");

        // create new question
        final var httpPostQuestion1 = new HttpPost(gameSessionServerAddress + "/api/questions");
        httpPostQuestion1.setEntity(new StringEntity(responseSessionDtoString));
        httpPostQuestion1.setHeader("Accept", "application/json");
        httpPostQuestion1.setHeader("Content-type", "application/json");
        final var questionHttpResponse1 = HttpClientBuilder.create().build().execute(httpPostQuestion1);

        assertThat(questionHttpResponse1.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        final var responseQuestionDtoString1 = IOUtil.toString(questionHttpResponse1.getEntity().getContent());
        assertThat(responseQuestionDtoString1).contains("questionId");

        // create new question
        final var httpPostQuestion2 = new HttpPost(gameSessionServerAddress + "/api/questions");
        httpPostQuestion2.setEntity(new StringEntity(responseSessionDtoString));
        httpPostQuestion2.setHeader("Accept", "application/json");
        httpPostQuestion2.setHeader("Content-type", "application/json");
        final var questionHttpResponse2 = HttpClientBuilder.create().build().execute(httpPostQuestion2);

        assertThat(questionHttpResponse2.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        final var responseQuestionDtoString2 = IOUtil.toString(questionHttpResponse2.getEntity().getContent());
        assertThat(responseQuestionDtoString2).contains("questionId");

        // create new question
        final var httpPostQuestion3 = new HttpPost(gameSessionServerAddress + "/api/questions");
        httpPostQuestion3.setEntity(new StringEntity(responseSessionDtoString));
        httpPostQuestion3.setHeader("Accept", "application/json");
        httpPostQuestion3.setHeader("Content-type", "application/json");
        final var questionHttpResponse3 = HttpClientBuilder.create().build().execute(httpPostQuestion3);
        assertThat(questionHttpResponse3.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);
    }
}
