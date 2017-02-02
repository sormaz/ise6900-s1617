/**
 * 
 */
package edu.ohio.ise.ise6900.model;

/**
 * @author sormaz
 *
 */
public class NotMemberException extends MfgException {

	/**
	 * 
	 */
	public NotMemberException() {

	}

	/**
	 * @param message
	 */
	public NotMemberException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public NotMemberException(Throwable cause) {
		super(cause);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotMemberException(String message, Throwable cause) {
		super(message, cause);

	}

}
