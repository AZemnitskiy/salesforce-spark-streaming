name := "salesforce-spark-connector"

version := "1.0"

scalaVersion := "2.11.4"
val sparkVersion = "2.4.4"

libraryDependencies ++= Seq ("org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
                             "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
                             "com.pontusvision.salesforce" % "emp-connector" % "0.0.2")

//unmanagedJars in Compile += file(Path.userHome + "/files/libs/amps/amps_client.jar")

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}