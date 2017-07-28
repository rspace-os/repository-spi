package com.researchspace.repository.spi.properties;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NumberRepoProperty extends RepoProperty {

	private Integer possibleValue;

	public NumberRepoProperty(String name, boolean required, Integer possibleValue) {
		super(name, RepositoryPropertyType.NUMBER, required);
		this.possibleValue = possibleValue;
	}

	@Override
	public Integer getPossibleValue() {
		return possibleValue;
	}

	public boolean isNumberType() {
		return true;
	}

	public Integer getNumber() {
		return possibleValue;
	}

	@JsonIgnore
	@Override
	public Boolean getBoolean() {
		return super.getBoolean();
	}

	@JsonIgnore
	@Override
	public List<String> getList() {
		return super.getList();
	}

	@JsonIgnore
	@Override
	public String getString() {
		return super.getString();
	}
}
