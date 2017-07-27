package com.researchspace.repository.spi;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default do-nothing implementation useful for test environments or test servers when there is no
 * 'real' data repository to connect to.
 * 
 * @author radams
 * 
 */
public class StubRepositoryImpl implements IRepository {
	Logger log = LoggerFactory.getLogger(StubRepositoryImpl.class);
	private boolean success = true;

	@Override
	public RepositoryOperationResult submitDeposit(IDepositor depositor, File toDeposit, SubmissionMetadata metadata,  RepositoryConfig repoCfg) {
		log.info("Dummy Repository datafile " + toDeposit.getName() + "submitted by user "
				+ depositor.getUniqueName());
		return new RepositoryOperationResult( success, null, null);
	}

	public String toString (){
		return "Stub repository";
	}

	@Override
	public void configure(RepositoryConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RepositoryOperationResult testConnection() {
		log.info("Testing connection");
		return new RepositoryOperationResult(success, null, null);
	}

	@Override
	public RepositoryConfigurer getConfigurer() {
		return RepositoryConfigurer.NULL_OPS;
	}

}
