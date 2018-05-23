<?xml version="1.0" ?>

<TestCase name="GET_basic_info_by_empty_profileId" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="04/18/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/08/2018" host="teamzombie-VirtualBox" />
</meta>

<id>602B718B434E11E89B1C024254D2B3F9</id>
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
          uid="6496E29C434E11E89B1C024254D2B3F9" 
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
      <header field="X-AEG-Profile-ID" value="" />
      <header field="Authorization" value="{{authenticationHeader}}" />
      <header field="X-ATT-ClientId" value="{{clientIdHeader}}" />
      <header field="Secure-Token-Id" value="" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="602B989D434E11E89B1C024254D2B3F9" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="602B989F434E11E89B1C024254D2B3F9" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="602B98A1434E11E89B1C024254D2B3F9" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


</TestCase>
