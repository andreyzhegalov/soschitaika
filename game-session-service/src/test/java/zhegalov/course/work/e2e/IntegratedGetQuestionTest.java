package zhegalov.course.work.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.plexus.util.IOUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class, MongoAutoConfiguration.class} )
public class IntegratedGetQuestionTest {
    @Configuration
    public static class TestContext{
    }
    @Value("${webserver.gamesession.address}")
    private String gameSessionServerAddress;

    @Test
    void getQuestionScenario() throws Exception{
        final var httpPost = new HttpPost(gameSessionServerAddress + "/api/sessions");
        String json = "{\"min\":0,\"max\":1,\"valueCnt\":5,\"operations\":[\"SUM\"]}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        final var response = HttpClientBuilder.create().build().execute(httpPost);

        final var sessionResponse = IOUtil.toString(response.getEntity().getContent());
        assertThat(sessionResponse).contains("sessionId");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
    }
}
