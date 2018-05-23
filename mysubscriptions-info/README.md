# profile-mysubscriptions-info
## Status UNKNOWN

## Synopsis

This is a sample service

<!-- This section describes the service, providing an overview of the -->
<!-- purpose and function, as well as specific limitations or exclusions if -->
<!-- helpful (e.g. this service does not do 'x', see service 'xyz'). -->


## Code Examples

Show how to call the service as concisely as possible. Developers
should be able to figure out how the service solves their problem by
looking at the code example. Make sure the API you describe is the
obvious and main use case for the service, and that your example code
is short and concise.

``` http
http://sandbox-kube.aeg.cloud/api/v1/proxy/namespaces/profile-mysubscriptions/services/https:info:443/service
```


## Dependencies & Prerequisites

List all dependencies and prerequisites for the project.


## Triggers

List specific issues that will have an impact on the roll-out of this
service. For example, if new versions of this service can only be
deployed to production during off-hours, etc.


## Manual Test Cases

List the manual tests that are required to certify this service - if
any - and link to the additional test details.

## Functional Test Cases

List the functional tests cases that will be executed to certify this service.
Functional test cases are automatically executed by Jenkins and reported to Qmetry. Functional test cases should be created through LISA.

#### LISA Project Directory
**Java:** ./src/test/lisa/mysubscriptions-info

**Node:** ./test/lisa/mysubscriptions-info


## See Also

Links to additional documentation, if any.



## Project Information
**Developers:**

Owner: as966n

### Build
**Jenkins Job:** http://egjenkins-he.dtveng.net/job/PROF_mysubscriptions-info

### Service URL's
**ICD:** http://sandbox-kube.aeg.cloud/api/v1/proxy/namespaces/profile-mysubscriptions/services/https:info:443/icd

**Documentation:** http://sandbox-kube.aeg.cloud/api/v1/proxy/namespaces/profile-mysubscriptions/services/https:info:443/docs

**Service:** http://sandbox-kube.aeg.cloud/api/v1/proxy/namespaces/profile-mysubscriptions/services/https:info:443/service

### Platform Resources
**JIRA:** http://egjira.dtvops.net - Bug and features tracking

**Clouds Dashboard:** https://cloudops.dtveng.net - Relevant links to every component in the platform

## Project's CLI Reference

### Service

````bash
$ java -jar ./target/{your-project-compiled}.jar
````

### Maven Tasks
Build and test
````bash
$ mvn clean install
````

Create Docker container
````bash
$ mvn docker
````
