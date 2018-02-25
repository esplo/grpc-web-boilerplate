name := "grpc-server"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.4",
  scalacOptions += "-feature",
  resolvers ++= Seq(
    Resolver.bintrayRepo("naftoligug", "maven"),
    Resolver.sonatypeRepo("snapshots"))
)

lazy val loggingDependencies = List(
  "org.slf4j" % "slf4j-nop" % "1.6.4" // <- disables logging
)

lazy val grpcDependencies = List(
  "io.grpc" % "grpc-netty" % com.trueaccord.scalapb.compiler.Version.grpcJavaVersion,
  "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % com.trueaccord.scalapb.compiler.Version.scalapbVersion
)

lazy val appDependencies = grpcDependencies ++ loggingDependencies

lazy val protocolBuffersGeneration = PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
lazy val app = project.in(file("app"))
  .settings(commonSettings: _*)
  .settings(protocolBuffersGeneration).settings {
  libraryDependencies ++= appDependencies
}
