package com.researchspace.repository.spi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Minimal set of information to make a submission to a repository. This will be
 * supplied by RSpace from the UI supplied by the user.
 * 
 * @author rspace
 *
 */
@Data
@NoArgsConstructor
public class SubmissionMetadata {
	/**
	 * Possibly empty but non-null list of authors
	 */
	private @NonNull List<IDepositor> authors;
	private @NonNull List<IDepositor> contacts;
	private List<String> subjects = new ArrayList<>();
	private String description, title;
	private boolean publish;
	/**
	 * An optional URL for the license. Whether <code>null</code> is acceptable or
	 * not depends on the value for {@link LicenseConfigInfo#isLicenseRequired()}
	 * defined by the implementation.
	 */
	private Optional<URL> license;
	/**
	 * An optional name for the license. Whether <code>null</code> is acceptable or
	 * not depends on the value for {@link LicenseConfigInfo#isLicenseRequired()}
	 * defined by the implementation.
	 */
	private Optional<String> licenseName;

	/**
	 * Other metadata properties specific to a particular repository instance. If
	 * there are no such properties this can remain empty.
	 */
	private Map<String, String> otherProperties = new TreeMap<>();

	/**
	 * Adds a property name-value pair
	 * 
	 * @param name
	 * @param value
	 */
	public void addProperty(String name, String value) {
		otherProperties.put(name, value);
	}

	/**
	 * Boolean test for whether this metadata contains information about the
	 * metadata property with the given name
	 * 
	 * @param propertyName
	 * @return <code>true</code> if this property is defined, <code>false</code>
	 *         otherwise.
	 */
	public boolean hasOtherProperty(String propertyName) {
		return otherProperties.containsKey(propertyName);
	}

}
