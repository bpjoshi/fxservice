package com.bpjoshi.fxservice.exception;


/**
 * @author bpjoshi(Bhagwati Prasad)
 * Exception class for Bad Requests
 */
public final class DataFormatException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public DataFormatException() {
        super();
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(Throwable cause) {
        super(cause);
    }
}
