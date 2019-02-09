*** Settings ***

Library    Remote     http://localhost:8270/
Suite Setup    Ammonite Initialization

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

TEST 1 : Run the Ammonite keywords that will send an HTTP Post request.
    Run Ammonite Post Request Script
    [Tags]    This_Is_An_Actual_Test

TEST 2 : Run the Ammonite keywords that will send an HTTP Get request.
    Run Ammonite Get Request Script
    [Tags]    This_Is_An_Actual_Test

TEST 3 : Run the Ammonite keywords that will perform an HTTP fuzz test.
    Run Scala Check
    Run Ammonite Fuzz Test Script
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
    Run Scala Check Data Generator    1

Run Ammonite Post Request Script
    ${ammonite}=    Run Ammonite HTTP Post Test
    Should Contain    ${ammonite}    "title":"qa"
    Log To Console    ${ammonite}
    Log    ${ammonite}

Run Ammonite Get Request Script
    ${ammonite}=    Run Ammonite HTTP Get Test
    Should Contain    ${ammonite}    "body":
    Log    ${ammonite}

Run Ammonite Fuzz Test Script
    [Documentation]   If you want to be really mean and try to crash your own application-under-test (do not try to crash the jsonplaceholder.typicode.com website!!!), then
    ...               change the "Repeat Keyword    5 times" to "Repeat Keyword    5 minutes"... or maybe "Repeat Keyword    60 minutes".
    ...               WARNING : IF YOU RUN THIS KEYWORD WITH A LONG DURATION IT'S AT YOUR OWN RISK.
    Repeat Keyword    5 times    Ammonite Fuzz Test

Ammonite Fuzz Test
    ${ammonite}=    Run Ammonite HTTP Fuzz Test
    Should Contain    ${ammonite}    500
    Log    ${ammonite}
