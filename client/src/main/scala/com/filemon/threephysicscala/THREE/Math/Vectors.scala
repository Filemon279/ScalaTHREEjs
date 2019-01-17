package physics.webapp.THREE.Math
import physics.webapp.THREE.Camera.Camera

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
@JSGlobal
class Vector extends js.Object {

}

@js.native
@JSGlobal("THREE.Vector3")
class Vector3(var x: Double, var y: Double, var z: Double) extends Vector {
  def unproject ( camera : Camera ): Vector3 = js.native
  def sub ( v : Vector3 ) : Vector3 = js.native
  def normalize () : Vector3 = js.native
  def multiplyScalar ( s : Double ) : Vector3 = js.native
  def copy ( v : Vector3 ) : Vector3 = js.native
  def add ( v : Vector3 ) : Vector3 = js.native
  def set (x: Double, y: Double, z: Double) : Unit = js.native
  override def clone() : this.type = js.native


  override def toString: String = "x: "+x+" | y: "+y+" | z: "+z

}

@js.native
@JSGlobal("THREE.Vector2")
class Vector2(var x: Double, var y: Double) extends Vector


