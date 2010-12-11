import sbt._


/**
 * Created by IntelliJ IDEA.
 * User: David Rosell - Redpill-Linpro
 * Date: 2010-12-11
 * Time: 02:23
 */

class MontyHallProject(info: ProjectInfo) extends DefaultProject(info) {
  val mavenLocal = "Local Maven Repository" at
    "file://" + Path.userHome + "/.m2/repository"

  val scalatoolsSnapshot = "Scala Tools Snapshot" at
    "http://scala-tools.org/repo-snapshots/"

  val scalatoolsRelease = "Scala Tools Snapshot" at
    "http://scala-tools.org/repo-releases/"

  override def libraryDependencies = Set("org.scalatest" % "scalatest" % "1.2" % "test->default") ++ super.libraryDependencies
}