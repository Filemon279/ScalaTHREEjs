package com.filemon.threephysicscala.Physics
import physics.webapp.Physics.Constants
import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Math.Vector3

class Verlet extends Constants {

  def calculatePosition(obj: Planet): Vector3 = {
    var xVal = 2 * obj.position().x - obj.lastPosition.x + ( (obj.force.x/obj.mass) * Math.pow(deltaT,2) )
    var yVal = 2 * obj.position().y - obj.lastPosition.y + ( (obj.force.y/obj.mass) * Math.pow(deltaT,2) )
    var zVal = 2 * obj.position().z - obj.lastPosition.z + ( (obj.force.z/obj.mass) * Math.pow(deltaT,2) )
    new Vector3(xVal, yVal, zVal)
  }

}
