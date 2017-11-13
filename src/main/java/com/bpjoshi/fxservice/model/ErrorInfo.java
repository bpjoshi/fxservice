package com.bpjoshi.fxservice.model;
/**
 * @author bpjoshi(Bhagwati Prasad)
 * Class for sending errors in Response
 */
public class ErrorInfo {
	public final String detail;
    public final String message;

    public ErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}
