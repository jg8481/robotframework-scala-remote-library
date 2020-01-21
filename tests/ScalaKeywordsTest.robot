*** Settings ***

Library    Remote     http://localhost:8270/

*** Test Cases ***

TEST 1 : Run the Say Something keyword from a remote library and it should display in the log.html result file.
    Say Something Scala
    [Tags]    This_Is_A_Simple_Test

TEST 2 : Run the Check String keyword from a remote library and it should be equal to String.
    Check String Scala
    [Tags]    This_Is_A_Simple_Test

TEST 3 : Run the Just Text Scala keyword from a remote library and it should display in the log.html result file.
    Just Text Scala
    [Tags]    This_Is_A_Simple_Test

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
