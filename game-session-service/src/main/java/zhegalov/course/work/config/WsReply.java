package zhegalov.course.work.config;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class WsReply {

    @SendTo("/topic/response")
    public String reply(String msg) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        return msg;
    }

}

