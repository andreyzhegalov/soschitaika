package zhegalov.course.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "zhegalov.course.work.feign")
public class GameSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameSessionApplication.class, args);
    }
}
