package com.researchspace.repository.spi.properties;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.ObjectMethodsGuru;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class StringRepoPropertyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToJson() throws JsonProcessingException {
		StringRepoProperty repoProp = createStringRepoProperty();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(repoProp);
		assertEquals("value", JsonPath.read(json, "$.string"));
	}

	private StringRepoProperty createStringRepoProperty() {
		return new StringRepoProperty("any", true, "value");
	}
	

}
