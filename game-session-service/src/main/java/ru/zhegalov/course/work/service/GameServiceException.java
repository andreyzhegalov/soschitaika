package ru.zhegalov.course.work.service;

public class GameServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GameServiceException() {
    }

    public GameServiceException(String msg) {
        super(msg);
    }

    public GameServiceException(Throwable cause) {
        super(cause);
    }
}
