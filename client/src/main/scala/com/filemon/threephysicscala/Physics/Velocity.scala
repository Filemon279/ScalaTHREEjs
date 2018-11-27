package physics.webapp.Physics
import physics.webapp.THREE.Math.Vector3

object Velocity extends Constants {

  def velocity(velocity: Vector3, inForce: Vector3): Vector3 = {
    val vX = velocity.x + (inForce.x/ earthMass ) * deltaT
    val vY = velocity.y + (inForce.y/ earthMass ) * deltaT
    val vZ = velocity.z + (inForce.z/ earthMass ) * deltaT
    new Vector3(vX, vY, vZ)
  }
}
