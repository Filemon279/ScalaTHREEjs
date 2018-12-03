package physics.webapp.Physics

import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Math.Vector3

object Spring {
  def springForce(obj1: Planet, obj2: Planet, stiffness: Double, distance: Double): Vector3 = {
    val distanceX = Math.sqrt( Math.pow( (obj1.position().x - obj2.position().x), 2 ) )
    val forceX = -stiffness * ( distanceX - distance) * (distanceX/Math.abs(distanceX))
    //val forceY = -stiffness * ( Math.abs(obj1.velocity.y ) - distance) * (obj1.velocity.y/Math.abs(obj1.velocity.y))
    //val forceZ = -stiffness * ( Math.abs(obj1.velocity.z ) - distance) * (obj1.velocity.z/Math.abs(obj1.velocity.z))
    println(""+forceX+" : "+0+ " : "+0)
    new Vector3(forceX, 0, 0)
  }
}
