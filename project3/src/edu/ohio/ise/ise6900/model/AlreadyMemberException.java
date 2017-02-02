package edu.ohio.ise.ise6900.model;

public class AlreadyMemberException extends MfgException {
	/**
	 * 
	 */
	public AlreadyMemberException() {

	}

	/**
	 * @param message
	 */
	public AlreadyMemberException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public AlreadyMemberException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public AlreadyMemberException(String message, Throwable cause) {
		super(message, cause);

	}
}
