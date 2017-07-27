package com.researchspace.repository.spi;

import java.util.List;
/**
 * Represents user information to supply to the repository. 
 * @author rspace
 *
 */
public interface IDepositor {
    
    /**
     * Gets the email address of the person making the deposit
     * @return  A non-<code>null</code> string
     */
     String getEmail();

     /**
      * A unique identifier for the person making the deposit- e.g. a user ID or username
      * @return A non-<code>null</code> string
      */
     String getUniqueName();
     
     /**
      * An optional list of external identifier. May be empty but not <code>null</code>.
      * @return
      */
     List<ExternalId> getExternalIds();

}
