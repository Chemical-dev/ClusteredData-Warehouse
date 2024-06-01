package com.clustereddatawarehouse.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class DealException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private final String responseCode;
	@Getter
	private final String message;

	public DealException(final String responseCode, final String message) {
		
		super(message);
		
		this.responseCode=responseCode;
		this.message=message;
	}
}
