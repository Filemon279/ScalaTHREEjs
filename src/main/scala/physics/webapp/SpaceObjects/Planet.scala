package physics.webapp.SpaceObjects

import physics.webapp.Physics.{Constants, Velocity}
import physics.webapp.Physicsimport.Force
import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh}
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Math.Vector3

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

trait PlanetParams {
  def mass: Double
  def radious: Double
  def velocity: Vector3
}

@JSExport
class Planet(geometry: Geometry, material: Material, _mass: Double, _radious: Double) extends PlanetParams with Constants {
  var mesh = new Mesh(geometry, material)
  var mass = _mass
  var radious = _radious
  var velocity = new Vector3(0,0,0)

  def initialPosition(x: Double, y: Double, z:Double): Unit = {
    mesh.position.x = x
    mesh.position.y = y
    mesh.position.z = z
  }

  def recalculatePosition(): Unit = {
    mesh.position.x = mesh.position.x + (velocity.x * deltaT)
    mesh.position.y = mesh.position.y + (velocity.y * deltaT)
    mesh.position.z = mesh.position.z + (velocity.z * deltaT)
    //var text = "X: "+mesh.position.x+" Y: "+mesh.position.y+"| Z:"+mesh.position.z
    //println(text)
  }

  def position(): Vector3 = {
    new Vector3(mesh.position.x, mesh.position.y, mesh.position.z)
  }

  def applyForce(force: Vector3): Unit = {
    velocity = Velocity.velocity(velocity, force)
  }
}
