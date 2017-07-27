package com.researchspace.repository.spi;

import static com.researchspace.repository.spi.properties.RepositoryPropertyType.BOOLEAN;
import static com.researchspace.repository.spi.properties.RepositoryPropertyType.LIST;
import static com.researchspace.repository.spi.properties.RepositoryPropertyType.NUMBER;
import static com.researchspace.repository.spi.properties.RepositoryPropertyType.STRING;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.researchspace.repository.spi.properties.BooleanRepoProperty;
import com.researchspace.repository.spi.properties.ListRepoProperty;
import com.researchspace.repository.spi.properties.NumberRepoProperty;
import com.researchspace.repository.spi.properties.RepoProperty;
import com.researchspace.repository.spi.properties.RepositoryPropertyType;
import com.researchspace.repository.spi.properties.StringRepoProperty;

public class TestRepoConfigurerTest {
    TestRepoConfigurer configurer = new TestRepoConfigurer();

    @Before
    public void setUp() throws Exception {
        configurer = new TestRepoConfigurer();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void propertyTypes() {
        NumberRepoProperty number = createANumberProperty("any");
        assertTrue(number.isNumberType());
        StringRepoProperty string = createAStringProperty("any");
        assertTrue(string.isStringType());
        BooleanRepoProperty booleanType = createABooleanProperty("any");
        assertTrue(booleanType.isBooleanType());
        ListRepoProperty listType = createAListProperty("any");
        assertTrue(listType.isListType());
    }

    @Test
    public void testGetOtherProperties() {
        configurer.addProperty("number", createANumberProperty("number"));
        configurer.addProperty("string", createAStringProperty("string"));
        configurer.addProperty("boolean", createABooleanProperty("boolean"));
        configurer.addProperty("a choice", createAListProperty("a choice"));

        assertEquals(4, configurer.getOtherProperties().size());
        assertHasPropertyOfType(configurer.getOtherProperties(), BOOLEAN);
        assertHasPropertyOfType(configurer.getOtherProperties(), NUMBER);
        assertHasPropertyOfType(configurer.getOtherProperties(), STRING);
        assertHasPropertyOfType(configurer.getOtherProperties(), LIST);

        assertTrue(getPropertyStream().filter(e -> e.isBooleanType()).findFirst().get().getBoolean());
        assertFalse(
                StringUtils.isBlank(getPropertyStream().filter(e -> e.isStringType()).findFirst().get().getString()));
        assertTrue(getPropertyStream().filter(e -> e.isNumberType()).findFirst().get().getNumber() > 0);
        assertTrue(getPropertyStream().filter(e -> e.isListType()).findFirst().get().getList().size() > 0);

    }

    private Stream<RepoProperty> getPropertyStream() {
        return configurer.getOtherProperties().values().stream();
    }

    private void assertHasPropertyOfType(Map<String, RepoProperty> props, RepositoryPropertyType type) {
        props.entrySet().stream().anyMatch(entry -> entry.getValue().getType().equals(type));
    }

    private ListRepoProperty createAListProperty(String name) {
        List<String> choices = Arrays.asList(new String[] { "c1", "c2" });
        return new ListRepoProperty(name, false, choices);
    }

    private BooleanRepoProperty createABooleanProperty(String name) {
        return new BooleanRepoProperty(name, true, Boolean.TRUE);
    }

    private StringRepoProperty createAStringProperty(String name) {
        return new StringRepoProperty(name, true, "a value");
    }

    private NumberRepoProperty createANumberProperty(String name) {
        return new NumberRepoProperty(name, true, 23);
    }

}
