package com.filemon.threephysicscala.SpaceObjects

import physics.webapp.Physics.{Constants, DeltaT, Velocity}
import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh, Points}
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Math.Vector3

class Particle(geometry: Geometry, material: Material, var mass: Double, var size: Double, var lifeTime: Double) extends Constants {

  var mesh = new Points(geometry, material)
  var velocity = new Vector3(0,0,0)
  var lastPosition: Vector3 = new Vector3(0,0,0)
  var force: Vector3 = new Vector3(0,0,0)
  var lived: Double = 0

  def setPosition(v: Vector3) = {
    mesh.position.x = v.x
    mesh.position.y = v.y
    mesh.position.z = v.z
  }

  def recalculateEulerPosition(): Unit = {
    //applyForce()
    mesh.position.x = mesh.position.x + (velocity.x * DeltaT.deltaT)
    mesh.position.y = mesh.position.y + (velocity.y * DeltaT.deltaT)
    mesh.position.z = mesh.position.z + (velocity.z * DeltaT.deltaT)

  }

  def applyForce(inForce: Vector3 = force): Unit = {
    velocity = Velocity.velocity(velocity, inForce)
  }

  def addForce(inForce: Vector3): Unit = {
    force.x += inForce.x
    force.y += inForce.y
    force.z += inForce.z
  }

}
