package com.researchspace.repository.spi;

import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
/**
 * Configures the server, apikey and repository  alias
 * @author rspace
 *
 */
@EqualsAndHashCode(of={"serverURL"})
@AllArgsConstructor
@ToString()
public class RepositoryConfig {

	private @Getter URL serverURL;
	/**
	 * Can be a username/ email/ api key
	 */
	private @Getter String identifier;
	/**
	 * An optional password, can be <code>null</code>
	 */
	private @Getter String password;
	/**
	 * A name of a repository  - optional. Required if a serverURL supports different repositories
	 */
	private @Getter String repositoryName;	

}
