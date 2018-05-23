<?xml version="1.0" ?>

<TestCase name="GET_basic_info_by_profile_id" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="04/16/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/08/2018" host="teamzombie-VirtualBox" />
</meta>

<id>C9D11FD641BD11E8BF0A0242B9154533</id>
<Documentation>GET and validate basic subscription info</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMSAoOS41LjEuNikmbm9kZXM9MTcxMzkwMzM4Mg==</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="http GET basic info" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="76B45ACB41BF11E8BF0A0242B9154533" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="http GET basic info" > 


      <!-- Data Sets -->
<readrec>Profile data</readrec>

      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check HTTP Response Code 200" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Check HTTP Response Code 200 checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<CheckResult assertTrue="false" name="Validate add-ons" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Assertion name: Validate add-ons checks for: false is of type: Ensure Result Equals.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$.addOnsInfo.addOnsCount</jsonPath>
        <expectedValue>{{NumOfAddOns}}</expectedValue>
        <ignoreArrayOrder>false</ignoreArrayOrder>
</CheckResult>

<CheckResult assertTrue="false" name="Validate package" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Assertion name: Validate package checks for: false is of type: Ensure Result Equals.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$.basePackageInfo.displayName</jsonPath>
        <expectedValue>{{packageName}}</expectedValue>
        <ignoreArrayOrder>false</ignoreArrayOrder>
</CheckResult>

<CheckResult assertTrue="false" name="Validate cdvr hours" type="com.ca.lisa.apptest.json.AssertJSONEquals2">
<log>Assertion name: Validate cdvr hours checks for: false is of type: Ensure Result Equals.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <jsonPath>$.cdvrInfo.availableHours</jsonPath>
        <expectedValue>{{cdvrHours}}</expectedValue>
        <ignoreArrayOrder>false</ignoreArrayOrder>
</CheckResult>

<url>{{protocol}}://{{kubeHost}}/{{basePath}}/{{infoPath}}</url>
<data-type>text</data-type>
      <header field="X-AEG-Profile-ID" value="{{profileId}}" />
      <header field="Authorization" value="{{authenticationHeader}}" />
      <header field="X-ATT-ClientId" value="{{clientIdHeader}}" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="C9D11FDC41BD11E8BF0A0242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="C9D11FDA41BD11E8BF0A0242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="C9D11FD841BD11E8BF0A0242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSheet" name="Profile data" atend="end" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAFdAALTnVtT2ZBZGRPbnN0AAozIFBBQ0tBR0VTdAATUHJvZmlsZSBkYXRhX1Jvd051bXQAATF0AAlwcm9maWxlSWR0AA4yOTkzOTE5MjRfVE1CUnQAC3BhY2thZ2VOYW1ldAANR290dGEgSGF2ZSBJdHQACWNkdnJIb3Vyc3QACDEwIEhPVVJTeA==</sample>
<table>
<col>profileId</col>
<col>packageName</col>
<col>NumOfAddOns</col>
<col>cdvrHours</col>
<tr>
<td>299391924_TMBR</td>
<td>Gotta Have It</td>
<td>3 PACKAGES</td>
<td>10 HOURS</td>
</tr>
<tr>
<td>301142095_TMBR</td>
<td>Go Big</td>
<td>0 PACKAGES</td>
<td>10 HOURS</td>
</tr>
<tr>
<td>test_base_pakage_cdvr</td>
<td>Gotta Have It</td>
<td>0 PACKAGES</td>
<td>100 HOURS</td>
</tr>
<tr>
<td>test_base_pakage_add_on_cdvr</td>
<td>Gotta Have It</td>
<td>2 PACKAGES</td>
<td>50 HOURS</td>
</tr>
</table>
    </DataSet>

</TestCase>
