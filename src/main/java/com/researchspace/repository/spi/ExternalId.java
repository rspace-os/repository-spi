package com.researchspace.repository.spi;

import lombok.NonNull;
import lombok.Value;

/**
 * An external identifier e.g. 'ORCID'.
 * 
 * @author rspace
 *
 */
@Value
public class ExternalId {
    /**
     * E.g. 'ORCID'
     */
    private @NonNull IdentifierScheme scheme;
    private @NonNull String identifier;
}
