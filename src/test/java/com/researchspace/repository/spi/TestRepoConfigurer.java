package com.researchspace.repository.spi;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.researchspace.repository.spi.properties.RepoProperty;

public class TestRepoConfigurer implements RepositoryConfigurer {

    private Map<String,  RepoProperty> others = new TreeMap<>();
    @Override
    public List<Subject> getSubjects() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public  Map<String, RepoProperty> getOtherProperties() {
        return others;
    }
    
    public TestRepoConfigurer addProperty (String name, RepoProperty value) {
        others.put(name, value);
        return this;
    }

}
