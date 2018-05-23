<?xml version="1.0" ?>

<TestCase name="GET_basic_info_by_ATS_token" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="04/16/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/11/2018" host="teamzombie-VirtualBox" />
</meta>

<id>B9E1599E524311E8BE570242B9154533</id>
<Documentation>GET basic subscription info by ATS token, validate that response contains all rereuiq</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMSAoOS41LjEuNikmbm9kZXM9LTE0NjIxOTY2NzU=</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node name="Generate AST token" log=""
          type="com.itko.lisa.utils.ExecSubProcessNode" 
          version="1" 
          uid="AF971527525211E8BE570242B9154533" 
          think="0H" 
          useFilters="true" 
          quiet="true" 
          next="http GET basic info" > 

<Subprocess>{{LISA_RELATIVE_PROJ_ROOT}}/Tests/Subprocesses/generate_ATS_token.tst</Subprocess>
<fullyParseProps>false</fullyParseProps>
<sendCommonState>false</sendCommonState>
<getCommonState>false</getCommonState>
<onAbort>abort</onAbort>
<Parameters>
</Parameters>
<SaveProps>
<save>atsToken</save>
</SaveProps>
    </Node>


    <Node name="http GET basic info" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="D7D3124C525211E8BE570242B9154533" 
          think="500-1S" 
          useFilters="true" 
          quiet="false" 
          next="Validate Json element of basic info" > 


      <!-- Filters -->
      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.http GET basic info.rsp</valueToFilterKey>
      <jsonPath>$.basePackageInfo.displayName</jsonPath>
      <valueProp>displayName</valueProp>
      <lengthProp>displayNamel</lengthProp>
      </Filter>

      <Filter type="com.itko.lisa.test.FilterSaveResponse">
        <valueToFilterKey>lisa.http GET basic info.rsp</valueToFilterKey>
      <prop>response</prop>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Check HTTP Response Code 200" type="com.itko.lisa.test.CheckResultHTTPResponseCode">
<log>Assertion name: Check HTTP Response Code 200 checks for: false is of type: HTTP Response Code.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <param>200</param>
</CheckResult>

<url>{{protocol}}://{{kubeHost}}/{{basePath}}/{{infoPath}}</url>
<content-type></content-type>
<data-type>text</data-type>
      <header field="Secure-Token-Id" value="{{atsToken}}" />
      <header field="Authorization" value="{{authenticationHeader}}" />
      <header field="X-ATT-ClientId" value="{{clientIdHeader}}" />
      <header field="X-AEG-Profile-ID" value="X-AEG-Profile-ID" />
<httpMethod>GET</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="Validate Json element of basic info" log=""
          type="com.itko.lisa.utils.ExecSubProcessNode" 
          version="1" 
          uid="BDC0D41531111E8BE570242B9154533" 
          think="0H" 
          useFilters="true" 
          quiet="true" 
          next="Validate Json element of basic info" > 


      <!-- Data Sets -->
<readrec>json element</readrec>
<Subprocess>{{LISA_RELATIVE_PROJ_ROOT}}/Tests/Subprocesses/validate Json element match regex.tst</Subprocess>
<fullyParseProps>false</fullyParseProps>
<sendCommonState>false</sendCommonState>
<getCommonState>false</getCommonState>
<onAbort>abort</onAbort>
<Parameters>
    <Parameter>
    <key>json</key>
    <value>{{response}}</value>
    <name>input json</name>
    </Parameter>
    <Parameter>
    <key>jsonPath</key>
    <value>{{jsonPath}}</value>
    <name>json path to filter</name>
    </Parameter>
    <Parameter>
    <key>regex</key>
    <value>{{regex}}</value>
    <name>regex to be matched</name>
    </Parameter>
</Parameters>
<SaveProps>
</SaveProps>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="B9E159A0524311E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="B9E159A1524311E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="B9E159A2524311E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.DataSheet" name="json element" atend="end" local="false" random="false" maxItemsToFetch="0" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAZ3CAAAAAgAAAADdAATanNvbiBlbGVtZW50X1Jvd051bXQAATF0AAVyZWdleHQAAi4rdAAIanNvblBhdGh0AB0kLmJhc2VQYWNrYWdlSW5mby5kaXNwbGF5TmFtZXg=</sample>
<table>
<col>jsonPath</col>
<col>regex</col>
<tr>
<td>$.basePackageInfo.displayName</td>
<td>.+</td>
</tr>
<tr>
<td>$.basePackageInfo.priceUsd</td>
<td>.+</td>
</tr>
<tr>
<td>$.basePackageInfo.colorCode</td>
<td>.+</td>
</tr>
<tr>
<td>$.addOnsInfo.addOnsCount</td>
<td>.+</td>
</tr>
<tr>
<td>$.addOnsInfo.addOns[*].priceUsd</td>
<td>.+</td>
</tr>
<tr>
<td>$.cdvrInfo.availableHours</td>
<td>.+</td>
</tr>
<tr>
<td>$.cdvrInfo.priceUsd</td>
<td>.+</td>
</tr>
<tr>
<td>$.nextBillingAmount</td>
<td>.+</td>
</tr>
<tr>
<td>$.basePackageInfo.channelCount</td>
<td>^\d+\+ Channels$</td>
</tr>
</table>
    </DataSet>

</TestCase>
