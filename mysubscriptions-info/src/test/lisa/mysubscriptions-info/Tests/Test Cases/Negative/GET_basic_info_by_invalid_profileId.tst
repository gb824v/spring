<?xml version="1.0" ?>

<TestCase name="GET_basic_info_by_invalid_profileId" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="04/18/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/11/2018" host="teamzombie-VirtualBox" />
</meta>

<id>67A1C33F434C11E89B1C024254D2B3F9</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMSAoOS41LjEuNikmbm9kZXM9LTkxMzAyOTg2NQ==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="http GET / My Subscription Response" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="6FCA8973434C11E89B1C024254D2B3F9" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check HTTP Response Code 401" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Response Code is not 401</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>401</param>
</CheckResult>

<CheckResult assertTrue="false" name="Ensure Unauthorized error" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log></log>
<then>fail</then>
        <jsonPath>$.error</jsonPath>
        <expectedValue>Unauthorized</expectedValue>
        <ignoreArrayOrder>false</ignoreArrayOrder>
</CheckResult>

<url>{{protocol}}://{{kubeHost}}/{{basePath}}/{{infoPath}}</url>
<content-type></content-type>
<data-type>text</data-type>
      <header field="X-AEG-Profile-ID" value="invalid_profile_id" />
      <header field="Authorization" value="{{authenticationHeader}}" />
      <header field="X-ATT-ClientId" value="{{clientIdHeader}}" />
      <header field="Secure-Token-Id" value="invalid_token" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="67A1C345434C11E89B1C024254D2B3F9" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="67A1C343434C11E89B1C024254D2B3F9" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="67A1C341434C11E89B1C024254D2B3F9" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
