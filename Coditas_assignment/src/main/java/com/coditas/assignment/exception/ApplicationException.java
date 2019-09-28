package com.coditas.assignment.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	private final ErrorCode errorCode;

	public ApplicationException(ErrorCode errorCode, String errorMsg) {

		super(errorMsg);
		this.errorCode = errorCode;
	}

	public ApplicationException(ErrorCode errorCode) {

		super();
		this.errorCode = errorCode;
	}

	public ErrorCode getCode() {
		return this.errorCode;
	}
}
