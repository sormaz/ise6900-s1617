package edu.ohio.ise.ise6900.model;

public class MfgException extends Exception {
	
	public MfgException() {

	}

	/**
	 * @param message
	 */
	public MfgException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public MfgException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public MfgException(String message, Throwable cause) {
		super(message, cause);

	}

}
