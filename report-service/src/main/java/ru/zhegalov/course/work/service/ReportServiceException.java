package ru.zhegalov.course.work.service;

public class ReportServiceException extends RuntimeException {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ReportServiceException() {

    }

    public ReportServiceException(String msg) {
        super(msg);
    }

    public ReportServiceException(Throwable cause) {
        super(cause);
    }

}

