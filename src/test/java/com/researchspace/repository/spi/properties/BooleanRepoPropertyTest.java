package com.researchspace.repository.spi.properties;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class BooleanRepoPropertyTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToJson() throws JsonProcessingException {
        NumberRepoProperty repoProp = createNumberRepoProperty();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(repoProp);
        assertEquals(23, ((Integer)JsonPath.read(json, "$.number")).intValue());
    }

    private NumberRepoProperty createNumberRepoProperty() {
        return new NumberRepoProperty("any", true, 23);
    }
    

}
