package com.researchspace.repository.spi.properties;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StringRepoProperty extends RepoProperty{

    private String possibleValue;
    private boolean stringType = true;

    public StringRepoProperty(String name, boolean required, String possibleValue) {
        super(name, RepositoryPropertyType.STRING, required);
        this.possibleValue = possibleValue;
    }

    @Override
    public String getPossibleValue() {
        return possibleValue;
    }
    
    @Override
    public String getString() {
        return possibleValue;
    }
    
    public boolean isStringType () {
        return stringType;
    }
    
    @JsonIgnore
    @Override
    public Boolean getBoolean() {
        return super.getBoolean();
    }
    
    @JsonIgnore
    @Override
    public Integer getNumber() {
        return super.getNumber();
    }
    @JsonIgnore
    @Override
    public List<String> getList() {
        return super.getList();
    }
}
