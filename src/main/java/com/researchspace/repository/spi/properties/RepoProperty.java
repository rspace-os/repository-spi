package com.researchspace.repository.spi.properties;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Definition of a non-standard property required by a repository deposit.
 * @author rspace
 * @since 0.1.6
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"name", "type"})
@AllArgsConstructor
@NoArgsConstructor
public abstract class RepoProperty {
    /**
     * A readable, but unique name for this property within the set of required properties
     */
	private String name;
	
	/**
	 * They data type of the value of this property
	 */
	private RepositoryPropertyType type;
	
	/**
	 * Whether this property requires a user-supplied value
	 */
	private boolean required;	
	
	/**
	 * Default value, or for ListRepoProperty a list of available choices to be selected by the user.
	 * @return
	 */
	public abstract Object getPossibleValue();
	
	public boolean isNumberType () {
		return false;
	}
	
	public boolean isStringType () {
		return false;
	}
	
	public boolean isBooleanType () {
		return false;
	}
	
	public boolean isListType () {
		return false;
	}
	
	public Integer getNumber() {
		throwUnsupported("number");
		return null;
	}
	
	public String getString() {
		throwUnsupported("string");
		return null;
	}
	
	public Boolean getBoolean() {
		throwUnsupported("boolean");
		return null;
	}
	
	public List<String> getList() {
		throwUnsupported("list");
		return null;
	}

	private void throwUnsupported(String type)  {
		throw new UnsupportedOperationException(String.format("This class does not support conversion to a %s for value [%s]", 
				type, getPossibleValue()));
	}

}
