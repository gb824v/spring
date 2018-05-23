<?xml version="1.0" ?>

<TestCase name="generate_ATS_token" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/01/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/08/2018" host="teamzombie-VirtualBox" />
</meta>

<id>F04752AD524F11E8BE570242B9154533</id>
<Documentation>Generate ATS token</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMSAoOS41LjEuNikmbm9kZXM9MTMyMzkyNDkxOQ==</sig>
<subprocess>true</subprocess>

<initState>
</initState>

<resultState>
    <Parameter>
    <key>atsToken</key>
    <value></value>
    <name>Set 1st in TGuard Call - Get atsToken (https POST)</name>
    </Parameter>
</resultState>

<deletedProps>
    <key>respType</key>
    <key>lisa.TGuard Call - Get atsToken (https POST).rsp</key>
    <key>clientIdHeader</key>
    <key>Layout_PATH</key>
    <key>Test Data for atsToken Generation_RowNum</key>
    <key>Composite_HOST</key>
    <key>infoPath</key>
    <key>client_type</key>
    <key>TG_OP</key>
    <key>rememberID</key>
    <key>Composite_PATH</key>
    <key>appID</key>
    <key>lisa.TGuard Call - Get atsToken (https POST).rsp.time</key>
    <key>rememberMe</key>
    <key>pwd</key>
    <key>client_version</key>
    <key>Protocol</key>
    <key>user</key>
    <key>TGuard_Path</key>
    <key>authenticationHeader</key>
    <key>TGuard_Host</key>
</deletedProps>

    <Node name="TGuard Call - Get atsToken (https POST)" log=""
          type="com.itko.lisa.ws.rest.RESTNode" 
          version="3" 
          uid="F04752AE524F11E8BE570242B9154533" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="end" > 


      <!-- Filters -->
      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.TGuard Call - Get atsToken (https POST).rsp</valueToFilterKey>
      <jsonPath>$.atsToken</jsonPath>
      <valueProp>atsToken</valueProp>
      <lengthProp></lengthProp>
      </Filter>


      <!-- Data Sets -->
<readrec>Test Data for atsToken Generation</readrec>
<url>https://{{TGuard_Host}}/{{TGuard_Path}}</url>
<content>password={{password}}&amp;client_type={{client_type}}&amp;appID={{appID}}&amp;client_version={{client_version}}&amp;respType=json&amp;rememberMe={{rememberMe}}&amp;rememberID={{rememberID}}&amp;TG_OP={{TG_OP}}&amp;userid={{user}}</content>
<data-type>text</data-type>
      <header field="Content-Type" value="application/x-www-form-urlencoded" />
      <header field="X-ATT-ClientId" value="synthetic" />
<httpMethod>POST</httpMethod>
<onError>abort</onError>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="F04752B1524F11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="F04752B0524F11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="F04752AF524F11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


    <DataSet type="com.itko.lisa.test.ExcelDataFile" name="Test Data for atsToken Generation" atend="" local="false" random="false" maxItemsToFetch="100" >
<sample>rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAKdAAIcmVzcFR5cGV0AARqc29udAAoVGVzdCBEYXRhIGZvciBhdHNUb2tlbiBHZW5lcmF0aW9uX1Jvd051bXQAATF0AApyZW1lbWJlcklEdAADb2ZmdAAFYXBwSUR0ABwzYVk3TEU3YjAzeHlyUFZ3QVRPcjRTWGtuMFE9dAAKcmVtZW1iZXJNZXQAA29mZnQAC2NsaWVudF90eXBldAADaU9TdAAOY2xpZW50X3ZlcnNpb250AClmN2Q3NTAzYy0xMmRhLTQ4MGUtOWU2ZS00ZjEwMTIyMDhmZGElMkYyMnQABVRHX09QdAAIVG9rZW5HZW50AANwd2R0AAc2Nzg1NDNjdAAEdXNlcnQAEgp0ZXN0XzUzNDE4Mjg2MDg0OHg=</sample>
    <location>{{LISA_RELATIVE_PROJ_ROOT}}/Data/ATS_token_dataset.xlsx</location>
    <sheetname>Sheet1</sheetname>
    </DataSet>

</TestCase>
