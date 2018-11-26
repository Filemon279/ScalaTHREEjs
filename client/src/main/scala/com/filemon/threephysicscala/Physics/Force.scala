package physics.webapp.Physicsimport
import physics.webapp.Physics.Constants
import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Geometryimport.Geometry.Mesh
import physics.webapp.THREE.Math.Vector3

object Force extends Constants{

  def gravitationForce(obj1: Planet, obj2: Planet): Vector3 = {
    val const: Double = 9.8 * ( (obj1.mass * obj2.mass) / Math.pow(5, 2) )
    val vec = makeVersor3(obj1.mesh.position, obj2.mesh.position)
    new Vector3(vec.x * const, vec.y * const, vec.z * const)
  }


  def dragSphereForce(obj1: Planet, density: Double): Vector3 = {
    val calc = -6 * Math.PI * density * obj1.radious
    new Vector3(obj1.velocity.x * calc, obj1.velocity.y * calc, obj1.velocity.z * calc)
  }

  def makeVersor3(v1: Vector3, v2: Vector3): Vector3 = {
    val newVector = new Vector3( (v1.x-v2.x), (v1.y-v2.y), (v1.z-v2.z) )
    val ver = 1 / ( Math.pow(newVector.x, 2) + Math.pow(newVector.y, 2) + Math.pow(newVector.z, 2) )
    new Vector3( ver*newVector.x, ver*newVector.y, ver*newVector.z)
  }


}
