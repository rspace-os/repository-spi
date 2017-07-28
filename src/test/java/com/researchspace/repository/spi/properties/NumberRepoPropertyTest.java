package com.researchspace.repository.spi.properties;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class NumberRepoPropertyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToJson() throws JsonProcessingException {
		BooleanRepoProperty repoProp = createBooleanRepoProperty();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(repoProp);
		assertEquals(true, ((Boolean) JsonPath.read(json, "$.boolean")).booleanValue());
	}

	private BooleanRepoProperty createBooleanRepoProperty() {
		return new BooleanRepoProperty("any", true, true);
	}

}
