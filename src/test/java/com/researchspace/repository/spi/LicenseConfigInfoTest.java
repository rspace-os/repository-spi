package com.researchspace.repository.spi;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LicenseConfigInfoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLicenseConfigInfoThrowsIAEIfConfigInvalid() {
		new LicenseConfigInfo(false, false, Collections.emptyList());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLicenseConfigInfoAllowDefaultThrowsIAEIfConfigInvalid() {
		new LicenseConfigInfo(true, false, Collections.emptyList());
	}

	@Test
	public void testLicenseConfigOKWIthNullList() {
		LicenseConfigInfo lci = new LicenseConfigInfo(true, true, null);
		assertNotNull(lci.getLicenses());
		assertTrue(lci.getLicenses().isEmpty());
		lci = new LicenseConfigInfo(true, true, Collections.emptyList());
		assertTrue(lci.getLicenses().isEmpty());
	}

}
