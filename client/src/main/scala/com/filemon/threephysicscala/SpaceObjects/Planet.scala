package physics.webapp.SpaceObjects

import physics.webapp.Physics.{Constants, DeltaT, Velocity}
import physics.webapp.Physics.Force
import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh}
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Math.Vector3

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

trait PlanetParams {
  def mass: Double
  def radious: Double
  def velocity: Vector3
}

@JSExportTopLevel("Planet")
class Planet(geometry: Geometry, material: Material, _mass: Double, _radious: Double) extends PlanetParams with Constants {
  var mesh = new Mesh(geometry, material)
  var mass = _mass
  var radious = _radious
  var velocity = new Vector3(0,0,0)
  var lastPosition: Vector3 = new Vector3(0,0,0)
  var force: Vector3 = new Vector3(0,0,0)

  def initialPosition(x: Double, y: Double, z:Double): Unit = {
    mesh.position.x = x
    mesh.position.y = y
    mesh.position.z = z
  }

  def recalculateEulerPosition(): Unit = {
    //applyForce()
    mesh.position.x = mesh.position.x + (velocity.x * DeltaT.deltaT)
    mesh.position.y = mesh.position.y + (velocity.y * DeltaT.deltaT)
    mesh.position.z = mesh.position.z + (velocity.z * DeltaT.deltaT)

  }


  def recalculateVerletPosition(): Unit = {
    lastPosition.x = mesh.position.x
    lastPosition.y = mesh.position.y
    lastPosition.z = mesh.position.z
    mesh.position.x = 2 * mesh.position.x - lastPosition.x + ( (force.x/mass) * Math.pow(DeltaT.deltaT,2) )
    mesh.position.y = 2 * mesh.position.y - lastPosition.y + ( (force.y/mass) * Math.pow(DeltaT.deltaT,2) )
    mesh.position.z = 2 * mesh.position.z - lastPosition.z + ( (force.z/mass) * Math.pow(DeltaT.deltaT,2) )
  }

  def recalculateFirstEulerPosition(): Unit = {
    lastPosition.x = mesh.position.x + (velocity.x * DeltaT.deltaT)
    lastPosition.y = mesh.position.y + (velocity.y * DeltaT.deltaT)
    lastPosition.z = mesh.position.z + (velocity.z * DeltaT.deltaT)
  }


  def position(): Vector3 = {
    new Vector3(mesh.position.x, mesh.position.y, mesh.position.z)
  }

  def setForce(inForce: Vector3): Unit = {
    force.x = inForce.x
    force.y = inForce.y
    force.z = inForce.z
  }

  def addForce(inForce: Vector3): Unit = {
    force.x += inForce.x
    force.y += inForce.y
    force.z += inForce.z
  }

  def applyForce(inForce: Vector3 = force): Unit = {
    velocity = Velocity.velocity(velocity, inForce)
  }
}
