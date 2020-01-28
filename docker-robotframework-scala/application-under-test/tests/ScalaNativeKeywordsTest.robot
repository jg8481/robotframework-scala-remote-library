*** Settings ***

Library    Remote     http://localhost:8270/
Library    OperatingSystem

Suite Setup    Ammonite Initialization
Suite Teardown    Remove All Files

*** Test Cases ***

SERVER CHECK : Run the Say Something keyword from a remote library and it should display in the log.html result file.
    Say Something Scala
    [Tags]    This_Is_Not_An_Actual_Test

SERVER CHECK : Run the Check String keyword from a remote library and it should be equal to String.
    Check String Scala
    [Tags]    This_Is_Not_An_Actual_Test

SERVER CHECK : Run the Just Text Scala keyword from a remote library and it should display in the log.html result file.
    Just Text Scala
    [Tags]    This_Is_Not_An_Actual_Test

TEST 1 : Run the Scala Native C Library keyword that will gradually increase physical memory utilization to check how well the environment handles it.
    Run Increased Memory Allocation Check
    [Tags]    This_Is_An_Actual_Test

TEST 2 : Run the Ammonite keywords that will enter letters into the Scala Native application executable through the command line. The Scala Native application will generate a message file.
    Run Ammonite Scala Native Executable Enter Letters Script
    [Tags]    This_Is_An_Actual_Test

TEST 3 : Run the Ammonite keywords that will enter numbers into the Scala Native application executable through the command line. The Scala Native application will generate a message file.
    Run Ammonite Scala Native Executable Enter Numbers Script
    [Tags]    This_Is_An_Actual_Test

TEST 4 : Run the Ammonite keywords that will enter random ScalaCheck fuzz test data into the Scala Native application executable through the command line. The Scala Native application will generate a message file.
    Run Scala Check
    Run Ammonite Scala Native Executable Fuzz Test Script
    [Tags]    This_Is_An_Actual_Test

TEST 5 : Run the Scala Native C Library keyword that will check physical memory utilization after fuzzing the Scala Native application executable with ScalaCheck.
    Run Memory Utilization Check
    [Tags]    This_Is_An_Actual_Test

TEST 6 : Run the Scala Native C Library keyword that will check CPU utilization after fuzzing the Scala Native application executable with ScalaCheck.
    Run CPU Utilization Check
    [Tags]    This_Is_An_Actual_Test

TEST 7 : Run Gatling load test simulation one and check the results from a Docker container.
    Run Gatling Load Test Simulation One In Docker Check
    [Tags]    This_Is_An_Actual_Test

TEST 8 : Run Gatling load test simulation two and check the results from a Docker container.
    Run Gatling Load Test Simulation Two In Docker Check
    [Tags]    This_Is_An_Actual_Test

*** Keywords ***

Say Something Scala
    ${val}=    Say Something    "Hello everyone"
    Should Be Empty   ${val}
    Log    ${val}

Check String Scala
    ${val}=    Check String    8675309
    Should Be Equal   ${val}   String
    Log To Console    ${val}
    Log    ${val}

Just Text Scala
    ${val}=    Just Text
    Should Be Equal   ${val}   Nothing special
    Log To Console    ${val}
    Log    ${val}

Run Scala Check
    [Documentation]   There are two types of arguments that can be passed to the Run Scala Check Data Generator
    ...               keyword, 1 or 2. Setting it to 1 will generate a medium amount of fuzz test data.
    ...               Setting it to 2 will generate a heavy amount of fuzz test data.
    Run Scala Check Data Generator In Docker    2

Run Ammonite Scala Native Executable Enter Letters Script
    Run Ammonite Scala Native Executable Enter Letters Test
    ${ammonite}=    Get File    /rfw/message.txt
    Should Contain    ${ammonite}    This part of the message was created with Scala Native using C
    Should Contain    ${ammonite}    This part of the message was created with robotframework-scala-remote-library Scala keywords
    Should Contain    ${ammonite}    Hello
    Log To Console    ${ammonite}
    Log    ${ammonite}

Run Ammonite Scala Native Executable Enter Numbers Script
    Run Ammonite Scala Native Executable Enter Numbers Test
    ${ammonite}=    Get File    /rfw/message.txt
    Should Contain    ${ammonite}    This part of the message was created with Scala Native using C
    Should Contain    ${ammonite}    This part of the message was created with robotframework-scala-remote-library Scala keywords
    Should Contain    ${ammonite}    12345
    Log To Console    ${ammonite}
    Log    ${ammonite}

Run Ammonite Scala Native Executable Fuzz Test Script
    [Documentation]   Try to find the breaking point of the Scala Native executable. For example, change the "Repeat Keyword    5 times"
    ...               to "Repeat Keyword    5 minutes"... or maybe "Repeat Keyword    60 minutes".
    Repeat Keyword    20 times    Ammonite Fuzz Test
    Run Ammonite Scala Native Executable Enter Letters Test

Ammonite Fuzz Test
    Run Ammonite Scala Native Executable Fuzz Test
    ${ammonite}=    Get File    /rfw/message.txt
    Should Contain    ${ammonite}    This part of the message was created with Scala Native using C
    Should Contain    ${ammonite}    This part of the message was created with robotframework-scala-remote-library Scala keywords
    Log    ${ammonite}

Run CPU Utilization Check
    Run Scala Native C Library CPU Utilization Check
    ${val}=    Get File    /rfw/tools/test-data-logs/scalanativecustomclibrary.txt
    Should Contain    ${val}    Current CPU utilization
    Should Not Contain    ${val}    Current CPU utilization = 1.
    Log To Console    ${val}
    Log    ${val}

Run Memory Utilization Check
    Run Scala Native C Library Memory Utilization Check
    ${val}=    Get File    /rfw/tools/test-data-logs/scalanativecustomclibrary.txt
    Should Contain    ${val}    Check current memory usage (no allocation)
    Should Not Contain    ${val}    NULL
    Should Not Contain    ${val}    Segmentation fault (core dumped)
    Log To Console    ${val}
    Log    ${val}

Run Increased Memory Allocation Check
    Run Scala Native C Library Increased Memory Allocation Check
    ${val}=    Get File    /rfw/tools/test-data-logs/scalanativecustomclibrary.txt
    Should Contain    ${val}    Wait then check memory usage again...
    Should Not Contain    ${val}    NULL
    Should Not Contain    ${val}    Segmentation fault (core dumped)
    Log To Console    ${val}
    Log    ${val}

Run Gatling Load Test Simulation One In Docker Check
    Run Keyword And Ignore Error    Empty Directory    /rfw/results
    Run Gatling Load Test Simulation One In Docker
    @{directory_list}=    List Directories In Directory    /rfw/results
    ${directory_one}=    Catenate    @{directory_list}
    ${log_file_one}=    Get File    /rfw/results/${directory_one}/simulation.log
    Should Not Contain    ${log_file_one}    KO
    Log    ${log_file_one}

Run Gatling Load Test Simulation Two In Docker Check
    Run Keyword And Ignore Error    Empty Directory    /rfw/results
    Run Gatling Load Test Simulation Two In Docker
    @{directory_list}=    List Directories In Directory    /rfw/results
    ${directory_two}=    Catenate    @{directory_list}
    ${log_file_two}=    Get File    /rfw/results/${directory_two}/simulation.log
    Should Not Contain    ${log_file_two}    KO
    Log    ${log_file_two}

Remove All Files
    Remove File    /rfw/message.txt
    Remove File    /rfw/simple-scala-native-example-application/message.txt
    Remove File    /rfw/tools/test-data-logs/fuzztestdata.txt
    Remove File    /rfw/tools/test-data-logs/scalachecktestdata.txt
    Remove File    /rfw/tools/test-data-logs/scalanativecustomclibrary.txt
