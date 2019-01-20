package com.filemon.threephysicscala

import physics.webapp.Physics.{Constants, DeltaT}
import physics.webapp.THREE.Math.Vector3

case class PhysicVertex(var pos: Vector3, var vertexMass: Double) extends Constants  {

  var prevPosition: Vector3 = new Vector3(pos.x, pos.y, pos.z)
  var force: Vector3 = new Vector3(0, 0, 0)

  var springDistnace: Double = 1;
  var initialDistances: Vector3 = new Vector3(0,0,0)

  def clearForces() = {
    force = new Vector3(0, 0, 0)
  }

  def addGravitationForce() = {
    force.y += - vertexMass * (EARTH_G/100)*DeltaT.gravity

    force.z += DeltaT.windForce
  }

  def addForce(f: Vector3) = {
    force.x -= f.x
    force.y -= f.y
    force.z -= f.z
  }

  def initFirstPosition() = {
    prevPosition.x = pos.x + DeltaT.deltaT * DeltaT.deltaT * force.x / vertexMass
    prevPosition.y = pos.y + DeltaT.deltaT * DeltaT.deltaT * force.y / vertexMass
    prevPosition.z = pos.z + DeltaT.deltaT * DeltaT.deltaT * force.z / vertexMass
  }

  def calculateEulerPosition() = {
    pos.x = pos.x + DeltaT.deltaT * DeltaT.deltaT * force.x / vertexMass
    pos.y = pos.y + DeltaT.deltaT * DeltaT.deltaT * force.y / vertexMass
    pos.z = pos.z + DeltaT.deltaT * DeltaT.deltaT * force.z / vertexMass
  }

  def calculateVerletPosition(attached: Boolean) = {
    val verletVector = generateVerletVector(attached)
    prevPosition = pos.clone()
    pos = verletVector
  }

  def collisionCheck() = {

  }

  def generateVerletVector(attached: Boolean) = {

    val verlet_x : Double = (2 * pos.x) - prevPosition.x + (DeltaT.deltaT * DeltaT.deltaT) * (force.x / vertexMass)
    val verlet_y : Double = (2 * pos.y) - prevPosition.y + (DeltaT.deltaT * DeltaT.deltaT) * (force.y / vertexMass)
    val verlet_z : Double = (2 * pos.z) - prevPosition.z + (DeltaT.deltaT * DeltaT.deltaT) * (force.z / vertexMass)

    if(attached) new Vector3(prevPosition.x, prevPosition.y, prevPosition.z)
    else new Vector3(verlet_x, verlet_y, verlet_z)
  }

  def bottomCollisionCheck() = {
    if( pos.y < 0 ) pos.y = prevPosition.y
  }

}