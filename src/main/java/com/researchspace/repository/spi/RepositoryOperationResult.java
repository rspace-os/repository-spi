package com.researchspace.repository.spi;

import java.net.URL;

/**
 * For service operations that return a {@link Boolean} indicating
 * success/failure, it can be useful to return an entity back to the controller
 * layer as well.
 * 
 * @author radams
 *
 */
public class RepositoryOperationResult {

	private boolean succeeded;
	private String message;
	private URL url;

	/**
	 * Human readable message from repository
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * A URL to a resource in the repository. If submission was successful, this
	 * should be a link to the submitted deposit.
	 * 
	 * @return A {@link URL}
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * Boolean test for success or not.
	 * 
	 * @return
	 */
	public boolean isSucceeded() {
		return succeeded;
	}

	/**
	 * @param entity
	 *            the object affected by the service method
	 * @param succeeded
	 * @param msg
	 *            Human-readable message from server
	 * @param resourceLink
	 *            An optional URL to view the deposit, may be <code>null</code>
	 */
	public RepositoryOperationResult(boolean succeeded, String msg, URL resourceLink) {
		super();
		this.succeeded = succeeded;
		this.message = msg;
		this.url = resourceLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + (succeeded ? 1231 : 1237);
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepositoryOperationResult other = (RepositoryOperationResult) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (succeeded != other.succeeded)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
}
