package com.researchspace.repository.spi.properties;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ListRepoProperty extends RepoProperty {

	private List<String> possibleValue;

	public ListRepoProperty(String name, boolean required, List<String> possibleValue) {
		super(name, RepositoryPropertyType.LIST, required);
		this.possibleValue = possibleValue;
	}

	@Override
	public List<String> getPossibleValue() {
		return possibleValue;
	}

	public boolean isListType() {
		return true;
	}

	public List<String> getList() {
		return possibleValue;
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
	public String getString() {
		return super.getString();
	}

}
