package zhegalov.course.work.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntityEnclosingRequest;
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

import zhegalov.course.work.controllers.dto.AnswerDto;

@Disabled
@SpringBootTest
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class, MongoAutoConfiguration.class })
public class IntegratedGetQuestionTest {
    @Configuration
    public static class TestContext {
    }

    private static final String API_ANSWER = "/api/answers";
    private static final String API_QUESTION = "/api/questions";
    private static final String API_SESSION = "/api/sessions";
    private static final String API_REPORT = "/api/reports";

    @Value("${webserver.gamesession.address}")
    private String gameSessionServerAddress;

    @Test
    void getQuestionScenario() throws Exception {
        // create new session
        final var jsonSettings = "{\"id\":null,\"questionCount\":2,\"gameSettings\":{\"min\":0,\"max\":10,\"valueCnt\":2,\"operations\":[\"SUM\"]}}";
        final var httpPostSession = new HttpPost(gameSessionServerAddress + API_SESSION);
        httpPostSession.setEntity(new StringEntity(jsonSettings));
        addHeaders(httpPostSession);
        final var sessionHttpResponse = HttpClientBuilder.create().build().execute(httpPostSession);
        assertThat(sessionHttpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        final var responseSessionDtoString = IOUtil.toString(sessionHttpResponse.getEntity().getContent());
        assertThat(responseSessionDtoString).contains("sessionId");

        // create new question
        final var httpPostQuestion1 = new HttpPost(gameSessionServerAddress + API_QUESTION);
        httpPostQuestion1.setEntity(new StringEntity(responseSessionDtoString));
        addHeaders(httpPostQuestion1);
        final var questionHttpResponse1 = HttpClientBuilder.create().build().execute(httpPostQuestion1);
        assertThat(questionHttpResponse1.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        final var responseQuestionDtoString1 = IOUtil.toString(questionHttpResponse1.getEntity().getContent());
        assertThat(responseQuestionDtoString1).contains("questionId");

        // answer to question
        final var httpPostAnswer1 = new HttpPost(gameSessionServerAddress + API_ANSWER);
        addHeaders(httpPostAnswer1);
        final var questionId1 = getQuestionId(responseQuestionDtoString1);
        httpPostAnswer1.setEntity(new StringEntity(makeAnswerBody(questionId1, "11")));
        final var answerHttpResponse1 = HttpClientBuilder.create().build().execute(httpPostAnswer1);
        assertThat(answerHttpResponse1.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

        // create new question
        final var httpPostQuestion2 = new HttpPost(gameSessionServerAddress + API_QUESTION);
        httpPostQuestion2.setEntity(new StringEntity(responseSessionDtoString));
        addHeaders(httpPostQuestion2);
        final var questionHttpResponse2 = HttpClientBuilder.create().build().execute(httpPostQuestion2);
        assertThat(questionHttpResponse2.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        final var responseQuestionDtoString2 = IOUtil.toString(questionHttpResponse2.getEntity().getContent());
        assertThat(responseQuestionDtoString2).contains("questionId");

        // answer to question
        final var httpPostAnswer2 = new HttpPost(gameSessionServerAddress + API_ANSWER);
        addHeaders(httpPostAnswer2);
        final var questionId2 = getQuestionId(responseQuestionDtoString2);
        httpPostAnswer2.setEntity(new StringEntity(makeAnswerBody(questionId2, "12")));
        final var answerHttpResponse2 = HttpClientBuilder.create().build().execute(httpPostAnswer2);
        assertThat(answerHttpResponse2.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

        // create new question
        final var httpPostQuestion3 = new HttpPost(gameSessionServerAddress + API_QUESTION);
        httpPostQuestion3.setEntity(new StringEntity(responseSessionDtoString));
        addHeaders(httpPostQuestion3);
        final var questionHttpResponse3 = HttpClientBuilder.create().build().execute(httpPostQuestion3);
        assertThat(questionHttpResponse3.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);

        // create report
        final var httpPostReport = new HttpPost(gameSessionServerAddress + API_REPORT);
        httpPostReport.setEntity(new StringEntity(responseSessionDtoString));
        addHeaders(httpPostReport);
        final var reportHttpResponse = HttpClientBuilder.create().build().execute(httpPostReport);
        assertThat(reportHttpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
    }

    private void addHeaders(HttpEntityEnclosingRequest request) {
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
    }

    private String getQuestionId(String response) throws JsonMappingException, JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        return objectMapper.readTree(response).get("questionId").asText();
    }

    private String makeAnswerBody(String questionId, String answer) throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        final var answerDto = new AnswerDto();
        answerDto.setQuestionId(questionId);
        answerDto.setAnswer(answer);
        return objectMapper.writeValueAsString(answerDto);
    }
}
