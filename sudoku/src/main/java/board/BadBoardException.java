package board;

public class BadBoardException extends RuntimeException {
    public BadBoardException() {
        super();
    }

    public BadBoardException(String message) {
        super(message);
    }

    public BadBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadBoardException(Throwable cause) {
        super(cause);
    }

    protected BadBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
