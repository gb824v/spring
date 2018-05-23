<?xml version="1.0" ?>

<TestCase name="GET_basic_info_by_missing_profileid" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="04/18/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/08/2018" host="teamzombie-VirtualBox" />
</meta>

<id>16D5EA7A524C11E8BE570242B9154533</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMSAoOS41LjEuNikmbm9kZXM9MTM3ODUxMzYwMg==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="http GET /My Subsription Response" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="16D5EA7B524C11E8BE570242B9154533" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="end" > 


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check HTTP Response Code 400" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Response Code is not 400</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>400</param>
</CheckResult>

<url>{{protocol}}://{{kubeHost}}/{{basePath}}/{{infoPath}}</url>
<data-type>text</data-type>
      <header field="Authorization" value="{{authenticationHeader}}" />
      <header field="X-ATT-ClientId" value="{{clientIdHeader}}" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="16D5EA7E524C11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="16D5EA7D524C11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="16D5EA7C524C11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


</TestCase>
