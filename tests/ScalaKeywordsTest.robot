*** Settings ***

Library    Remote     http://localhost:8270/

*** Test Cases ***

Run the Say Something keyword from the remote library and it should display "Hello everyone" in the log.html result file.
    Say Something Scala

Run the Check String keyword from the remote library, log it to the console, and it should be equal to String.
    Check String Scala

Run the Just Text Scala keyword from the remote library, log it to the console, and it should be equal to "Nothing special".
    Just Text Scala

*** Keywords ***

Say Something Scala
    ${val}=    Say Something    "Hello everyone"
    Should Be Empty   ${val}

Check String Scala
    ${val}=    Check String    8675309
    Log    ${val}
    Log To Console    ${val}
    Should Be Equal   ${val}   String

Just Text Scala
    ${val}=    Just Text
    Log    ${val}
    Log To Console    ${val}
    Should Be Equal   ${val}   Nothing special
