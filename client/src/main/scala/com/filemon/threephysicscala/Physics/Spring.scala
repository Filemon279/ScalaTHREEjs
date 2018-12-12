package physics.webapp.Physics

import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Math.Vector3

object Spring {
  def springForce(obj1: Planet, obj2: Planet, stiffness: Double, distance: Double): Vector3 = {
    val distanceX = Math.sqrt( Math.pow( (obj1.position().x - obj2.position().x), 2 ) )
    val distanceY = Math.sqrt( Math.pow( (obj1.position().y - obj2.position().y), 2 ) )
    val distanceZ = Math.sqrt( Math.pow( (obj1.position().z - obj2.position().z), 2 ) )
    val forceX = -stiffness * ( distanceX - distance) * (distanceX/Math.abs(distanceX))
    val forceY = -stiffness * ( distanceY - distance) * (distanceY/Math.abs(distanceY))
    val forceZ = -stiffness * ( distanceZ - distance) * (distanceZ/Math.abs(distanceZ))
    new Vector3(forceX, forceY, forceZ)
  }
}
