# repository-spi

Welcome to RSpace's Service Provider Interface (SPI) for repository connectors. This project
provides interfaces and supporting classes to be used by developers wanting to add their repository
to those currently supported by RSpace (Figshare, Dataverse, DSpace).

## Nomenclature

**provider** A repository that has implemented this SPI

## Overview of RSpace export process. 

* When users export from RSpace web application, they can choose the type of export (HTML, XML, PDF or MSWord) and optionally 
a destination for the export. 
* RSpace then generates a form, based on configuration data from the **provider** to define metadata for their export
 that will be sent to the repository. 
* Users fill in the form and submit.
* RSpace generates the export, and calls the appropriate **provider** with the deposit, metadata, and authentication
  information.
* The **provider** submits the RSpace export and metadata to their repository
* **provider** returns a status message to RSpace describing the success or failure of the operation.

This workflow is described in the sequence diagram below.

## Overview of implementation process for providers

* Create a new Java-based project that depends on this project.
* Provide an implementation of the [IRepositoryConfigurer](src/main/java/com/researchspace/repository/spi/IRepositoryConfigurer)
 and [IRepository](src/main/java/com/researchspace/repository/spi/IRepository) interfaces that configure the submission form,
 and process the submission, respectively.
* License your project.
* Build  a jar of the project and send to us, ideally along with source code so that we can review for security and 
   check that dependencies won't cause any problems in RSpace. There is a small amount of work to do in the RSpace UI by RSpace to set
   up your repository as a new 'App' that users can enable and register their account details, if needed.
* When all is working, you'll be able to add your jar file to RSpace and it will be detected and loaded automatically.

## Implementation guidance 

* Source code must be Java 8 compatible
* Please write some unit tests, especially for the IRepository#submitDeposit() method, so that you know your 
   code works.
* There are some minimum versions of 3rd party libraries to use. If you use these libraries in your implementation,
  please use those listed below
  
  
## Minimum library versions

| Library | Version |
| -------- | -------- |
| Junit | 4.12 |
| apache-commons-lang | 2.6 |