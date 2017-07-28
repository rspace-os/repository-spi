package com.researchspace.repository.spi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

import lombok.Value;

/**
 * Encapsulates information about what license information is needed.
 * 
 * @author rspace
 *
 */
@Value
public class LicenseConfigInfo {

	/**
	 * Public constructor.
	 * 
	 * @param licenseRequired
	 *            whether the license is mandatory or not
	 * @param otherLicensePermitted
	 *            whether a user-supplied license is permissible. This must be true
	 *            if <code>licenses</code> is empty
	 * @param licenses
	 *            Optional list of recommended licenses, can be <code>null</code>
	 * @throws IllegalArgumentException
	 *             if <code>licenses</code> is empty and
	 *             <code>otherLicensePermitted</code> is false (see RSPAC-1157
	 *             license decision table condition 5).
	 */
	public LicenseConfigInfo(boolean licenseRequired, boolean otherLicensePermitted, List<License> licenses) {
		super();
		Validate.isTrue(!((licenses == null || licenses.isEmpty()) && !otherLicensePermitted),
				"If licenses are empty, you must allow 'otherLicensePermitted' to be true");
		this.licenseRequired = licenseRequired;
		this.otherLicensePermitted = otherLicensePermitted;
		this.licenses = licenses == null ? new ArrayList<License>() : licenses;
	}

	/**
	 * Boolean test for whether license information must be included with the
	 * deposit <br/>
	 * 
	 * @return a {@link boolean}
	 * @since 0.1.7
	 */
	private boolean licenseRequired;

	/**
	 * Boolean test for whether any arbitrary license can be used by the repository.
	 * <br/>
	 * 
	 * @return a {@link boolean}
	 * @since 0.1.7
	 */
	private boolean otherLicensePermitted;

	/**
	 * List of recommended licenses.
	 */
	private List<License> licenses;
}
