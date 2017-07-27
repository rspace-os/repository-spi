package com.researchspace.repository.spi;

import lombok.Data;
import lombok.NonNull;

/**
 * Encapsulates a subject or category
 * @author rspace
 *
 */
@Data
public class Subject {
	/**
	 * Optional id
	 */
	private Long id;
	/**
	 * Non-null
	 */
	private @NonNull String name;
	/**
	 * Optional parent to allow hierarchical structures
	 */
	private Long parentSubject;

}
