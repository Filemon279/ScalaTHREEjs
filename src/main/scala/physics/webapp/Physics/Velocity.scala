package physics.webapp.Physics
import physics.webapp.THREE.Math.Vector3

object Velocity extends Constants {

  def velocity(velocity: Vector3, force: Vector3): Vector3 = {
    val vX = velocity.x + (force.x/ earthMass ) * deltaT
    val vY = velocity.y + (force.y/ earthMass ) * deltaT
    val vZ = velocity.z + (force.z/ earthMass ) * deltaT
    new Vector3(vX, vY, vZ)
  }
}
