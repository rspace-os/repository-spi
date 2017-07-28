package com.researchspace.repository.spi.properties;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BooleanRepoProperty extends RepoProperty {

	private Boolean possibleValue;

	public BooleanRepoProperty(String name, boolean required, Boolean defaultValue) {
		super(name, RepositoryPropertyType.BOOLEAN, required);
		this.possibleValue = defaultValue;
	}

	@Override
	public Boolean getPossibleValue() {
		return possibleValue;
	}

	public boolean isBooleanType() {
		return true;
	}

	public Boolean getBoolean() {
		return possibleValue;
	}

	@JsonIgnore
	@Override
	public List<String> getList() {
		return super.getList();
	}

	@JsonIgnore
	@Override
	public Integer getNumber() {
		return super.getNumber();
	}

	@JsonIgnore
	@Override
	public String getString() {
		return super.getString();
	}

}
