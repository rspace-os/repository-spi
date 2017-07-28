package com.researchspace.repository.spi;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.researchspace.repository.spi.properties.RepoProperty;

/**
 * Returns information about how the deposit can be configured by the client.
 * <br/>
 * Provides read-only methods which can be used to construct or configure a UI
 * for repository submission.
 * 
 * @author rspace
 *
 */
public interface RepositoryConfigurer {

	RepositoryConfigurer NULL_OPS = new RepositoryConfigurer() {
		@Override
		public List<Subject> getSubjects() {
			return Collections.emptyList();
		}
	};

	/**
	 * Gets a list of possible subjects/categories for this repository
	 * 
	 * @return A non-null but possibly empty list of {@link Subject}
	 */
	List<Subject> getSubjects();

	/**
	 * Gets configurable list of possible licenses for this repository. <br/>
	 * The default implementation provided by this interface returns an empty list,
	 * but allows user-supplied licenses.
	 * 
	 * @return a {@link LicenseConfigInfo}
	 * @since 0.1.6
	 */
	default LicenseConfigInfo getLicenseConfigInfo() {
		return new LicenseConfigInfo(false, true, Collections.emptyList());

	}

	/**
	 * Gets an optional list of additional repository metadata properties that can
	 * be configured by the end-user. <br/>
	 * The default implementation returns an empty map.
	 * 
	 * @return a Map <String, RepoProperty>
	 */
	default Map<String, RepoProperty> getOtherProperties() {
		return Collections.emptyMap();
	}
}
