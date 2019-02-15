package scalaremotelibrary

import org.robotframework.javalib.library.AnnotationLibrary
import org.robotframework.remoteserver.RemoteServer
import ScalaRemoteLibraryServer._


object ScalaRemoteLibraryServer {

  def main(args: Array[String]): Unit = {
    RemoteServer.configureLogging()
    val server: RemoteServer = new RemoteServer()
    server.addLibrary(classOf[ScalaRemoteLibraryServer], 8270)
    server.start()
  }

}

class ScalaRemoteLibraryServer
    extends AnnotationLibrary("scalaremotelibrary/keywords/*Keywords.class") {

  override def getKeywordDocumentation(keywordName: String): String = {
    if (keywordName.==("__intro__")) {
      "Intro"
    }
    super.getKeywordDocumentation(keywordName)
  }

}
