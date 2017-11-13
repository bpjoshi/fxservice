package com.bpjoshi.fxservice.exception;

/**
 * @author bpjoshi(Bhagwati Prasad)
 * 404 Errors
 */
public class ResourceNotFoundException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}