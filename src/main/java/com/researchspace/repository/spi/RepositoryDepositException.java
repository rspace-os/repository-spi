package com.researchspace.repository.spi;


/**
 * Wraps underlying thrown data repository exceptions
 * @author radams
 *
 */
public class RepositoryDepositException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryDepositException(Exception cause, String msg) {
		super(msg, cause);
	}

	public RepositoryDepositException(Exception e) {
		super(e);
	}

	public RepositoryDepositException(String errorMsg) {
		super(errorMsg);
	}

}
