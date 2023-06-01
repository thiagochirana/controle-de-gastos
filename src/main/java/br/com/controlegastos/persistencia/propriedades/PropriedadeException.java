package br.com.controlegastos.persistencia.propriedades;

public class PropriedadeException extends Exception {
    public PropriedadeException() {
        super();
    }

    public PropriedadeException(String message) {
        super(message);
    }

    public PropriedadeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropriedadeException(Throwable cause) {
        super(cause);
    }

    protected PropriedadeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
