package com.qamercenaryforhire.robotframework.scalaremotelibrary.keywords

import org.robotframework.javalib.annotation.ArgumentNames

import org.robotframework.javalib.annotation.RobotKeyword

import org.robotframework.javalib.annotation.RobotKeywords


@RobotKeywords
class ScalaKeywords {

  @RobotKeyword("Say Something")
  @ArgumentNames(Array("things"))
  def saySomething(things: String): Unit = {
    println(things)
  }

  @RobotKeyword("Check String")
  @ArgumentNames(Array("parameters"))
  def checkString(parameters: AnyRef): String = parameters.getClass.getSimpleName

  @RobotKeyword("Just Text")
  def justText(): String = "Nothing special"

}
