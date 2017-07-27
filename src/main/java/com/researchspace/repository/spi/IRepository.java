package com.researchspace.repository.spi;

import java.io.File;


/**
 * Top level interface for data repository integration. This is loosely an an abstraction over our
 *   repository implementations - Dataverse, Figshare and DSpace.
 * @author radams
 *
 */
public interface IRepository {
    /**
     * Null implementation that does nothing, but avoids the need for null-checks when using in a test
     *  environment.
     */
    IRepository NULL_OPS = new IRepository (){


        @Override
        public RepositoryOperationResult submitDeposit(IDepositor depositor, File toDeposit, SubmissionMetadata metadata, RepositoryConfig repoCfg) {
            return new RepositoryOperationResult(false, "noops", null);
        }
        /**
         * Perform any configuration on the repository object by clients
         * @param config A {@link RepositoryConfig} 
         */
        public  void configure (RepositoryConfig config) {}

        @Override
        public RepositoryOperationResult testConnection() {
            return new RepositoryOperationResult(false, "noops", null);
        }

        @Override
        public RepositoryConfigurer getConfigurer() {
            return RepositoryConfigurer.NULL_OPS;
        }    
    };

    
    /**
     * Perform any configuration on the repository object by clients
     * @param config A {@link RepositoryConfig} 
     */
     void configure (RepositoryConfig config);
    
    /**
     * Performs the submission of the deposit
     * @param depositor the User performing the deposit
     * @param toDeposit the file to deposit to the archive
     * @param metadata a {@link SubmissionMetadata}
     * @throws RepositoryDepositException - wraps any low level implementation exception.
     */
    RepositoryOperationResult submitDeposit(IDepositor depositor, File toDeposit, SubmissionMetadata metadata,  RepositoryConfig repoCfg);

    /**
     * Should be a read-only, quick operation that verifies connection to the repository
     * @return A success message if connection is OK
     * @throws a runtime {@link IllegalStateException} if connection not OK
     */
      RepositoryOperationResult testConnection();
      
      /**
       * Gets an object to help configure the repository submission.
       * @return
       */
      RepositoryConfigurer getConfigurer();

}
