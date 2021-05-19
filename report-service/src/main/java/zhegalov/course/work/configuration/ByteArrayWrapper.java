package zhegalov.course.work.configuration;

import java.io.ByteArrayOutputStream;

import lombok.Getter;

@Getter
public class ByteArrayWrapper {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
}

