<?xml version="1.0" ?>

<TestCase name="validate Json element match regex" version="5">

<meta>
   <create version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/08/2018" host="teamzombie-VirtualBox" />
   <lastEdited version="9.5.1" buildNumber="9.5.1.6" author="admin" date="05/08/2018" host="teamzombie-VirtualBox" />
</meta>

<id>E5371557530E11E8BE570242B9154533</id>
<Documentation>Validate a Json element exists and matches a regex</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj05LjUuMSAoOS41LjEuNikmbm9kZXM9ODgzMTQ4Mzg0</sig>
<subprocess>true</subprocess>

<initState>
    <Parameter>
    <key>json</key>
    <value></value>
    <name>input json</name>
    </Parameter>
    <Parameter>
    <key>jsonPath</key>
    <value></value>
    <name>json path to filter</name>
    </Parameter>
    <Parameter>
    <key>regex</key>
    <value>.+</value>
    <name>regex to be matched</name>
    </Parameter>
</initState>

<resultState>
</resultState>

<deletedProps>
    <key>lisa.Output Log Message~1.rsp</key>
    <key>clientIdHeader</key>
    <key>lisa.Do-Nothing Step.rsp.time</key>
    <key>jsonValue</key>
    <key>jsonLength</key>
    <key>lisa.Do-Nothing Step.rsp</key>
    <key>infoPath</key>
    <key>lisa.Output Log Message.rsp</key>
    <key>lisa.continue (quiet).rsp</key>
    <key>lisa.Output Log Message~1.rsp.time</key>
    <key>lisa.continue (quiet).rsp.time</key>
    <key>lisa.Output Log Message.rsp.time</key>
    <key>authenticationHeader</key>
    <key>TGuard_Path</key>
    <key>TGuard_Host</key>
</deletedProps>

    <Node name="Output Log Message" log="Validate json element : {{jsonPath}}"
          type="com.itko.lisa.test.TestNodeLogger" 
          version="1" 
          uid="48EAB535531511E8BE570242B9154533" 
          think="500-1S" 
          useFilters="true" 
          quiet="true" 
          next="end" > 


      <!-- Filters -->
      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.Output Log Message.rsp</valueToFilterKey>
      <jsonPath>{{jsonPath}}</jsonPath>
      <valueProp>jsonValue</valueProp>
      <lengthProp>jsonLength</lengthProp>
      </Filter>


      <!-- Assertions -->
<CheckResult assertTrue="false" name="Ensure that the element is found" type="com.itko.lisa.test.AssertByScript">
<log>Assertion name: Ensure that the element is found checks for: false is of type: Assert by Script Execution.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <script>return jsonLength!= -1; </script>
        <language>BeanShell</language>
        <copyprops>TestExecPropsAndSystemProps</copyprops>
</CheckResult>

<CheckResult assertTrue="false" name="Ensure json element Matches Expression" type="com.itko.lisa.test.CheckResultPropRegEx">
<log>Assertion name: Ensure json element Matches Expression checks for: false is of type: Property Value Expression.</log>
<then>fail</then>
<valueToAssertKey></valueToAssertKey>
        <prop>jsonValue</prop>
        <param>{{regex}}</param>
</CheckResult>

<log>{{json}}</log>
    </Node>


    <Node name="abort" log=""
          type="com.itko.lisa.test.AbortStep" 
          version="1" 
          uid="E5373C69530E11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="" > 

    </Node>


    <Node name="fail" log=""
          type="com.itko.lisa.test.Abend" 
          version="1" 
          uid="E5373C6B530E11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="abort" > 

    </Node>


    <Node name="end" log=""
          type="com.itko.lisa.test.NormalEnd" 
          version="1" 
          uid="E5373C6D530E11E8BE570242B9154533" 
          think="0h" 
          useFilters="true" 
          quiet="true" 
          next="fail" > 

    </Node>


</TestCase>
