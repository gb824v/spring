# profile-mysubscriptions-info
[![Build Status](http://egjenkins-he.dtveng.net/job/PROF_mysubscriptions-info/job/master/badge/icon)](http://egjenkins-he.dtveng.net/job/PROF_mysubscriptions-info/job/master/)

## Synopsis
**service/info**

This service returns a JSON object containing information regarding user's subscriptions

**service/carousel**

This returns information required to create the my subscription carousels 


## API end points for client
**service/info**
``` 
https://api-staging.aeg.cloud/profile/mysubscriptions/info/service/info
``` 
**service/carousel**
``` 
https://api-staging.aeg.cloud/profile/mysubscriptions/info/service/carousel
``` 

## Request Parameters
 \# | Name | Parameter Type | Required  | Description 
------------ | ------------- | ------------- | ------------- | ------------- 
1 | X-AEG-Profile-ID | Header |Mandatory | Unique profile identifier of the profile. Used to query couchbase for eProfile
2 | Secure-Token-Id | Header | Mandatory | Token id used to get the session id from QPUMS

## Sample Response
```json
{
    "basePackageInfo": {
        "displayName": "Just Right",
        "channelCount": "80+ Live Channels",
        "priceUsd": "50.00",
        "colorCode": {
            "startCode": "C4EA80",
            "endCode": "429321"
        }
    },
    "addOnsInfo": {
        "addOnsCount": "6 Packages",
        "totalPriceUsd": "41.0",
        "addOns": [{
            "displayName": "HBO",
            "channelId": 0,
            "priceUsd": "5.00"
        }, {
            "displayName": "SHOWTIME",
            "channelId": 0,
            "priceUsd": "8.00"
        }, {
            "displayName": "CINEMAX",
            "channelId": 0,
            "priceUsd": "5.00"
        }, {
            "displayName": "STARZ",
            "channelId": 0,
            "priceUsd": "8.00"
        }, {
            "displayName": "Español",
            "channelId": 0,
            "priceUsd": "10.00"
        }, {
            "displayName": "Deportes",
            "channelId": 0,
            "priceUsd": "5.00"
        }]
    },
    "nextBillingAmount": "60.00",
    "cdvrInfo": {
        "availableHours": "20 Hours",
        "priceUsd": "0.00"
    }
}
```

## Dependencies & Prerequisites

This service needs Couchbase and QPUMS to work properly


## Functional Test Cases

List the functional tests cases that will be executed to certify this service.
Functional test cases are automatically executed by Jenkins and reported to Qmetry. Functional test cases should be created through LISA.

#### LISA Project Directory
**Java:** ./src/test/lisa/mysubscriptions-info

**Node:** ./test/lisa/mysubscriptions-info


## See Also

**Dev set up for testing In-App purchases**
https://egconfluence.dtveng.net/pages/viewpage.action?pageId=48225536



## Project Information
### Developers:
**Scrumbledore's Army** https://egconfluence.dtveng.net/display/ScrumDore/Scrumbledore%27s+Army+Home

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
