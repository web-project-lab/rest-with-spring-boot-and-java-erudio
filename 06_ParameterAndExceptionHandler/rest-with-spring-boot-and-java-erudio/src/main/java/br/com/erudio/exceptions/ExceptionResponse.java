package br.com.erudio.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date timesatamp;
	private String message;
	private String details;
	public ExceptionResponse(Date timesatamp, String message, String details) {
		super();
		this.timesatamp = timesatamp;
		this.message = message;
		this.details = details;
	}
	

	
	public Date getTimesatamp() {
		return timesatamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	

}
