package zhegalov.course.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "zhegalov.course.work.feign")
@SpringBootApplication
public class GameSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameSessionApplication.class, args);
    }
}
