package zhegalov.course.work.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Data<T> {
    @Getter
    private final T data;
}

