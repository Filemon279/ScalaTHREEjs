
enablePlugins(ScalaJSPlugin)

name := "scala-physics-model"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6"
libraryDependencies += "org.querki" %%% "jquery-facade" % "1.2"

scalaJSUseMainModuleInitializer := true

jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
