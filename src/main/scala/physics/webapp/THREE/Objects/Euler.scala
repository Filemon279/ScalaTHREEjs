package physics.webapp.THREE.Objects
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("THREE.Euler")
class Euler  extends js.Object {
  def this(x: Double = js.native, y: Double = js.native, z: Double = js.native, order: String = js.native) = this()
  var x: Double = js.native
  var y: Double = js.native
  var z: Double = js.native
  var order: String = js.native
  def set(x: Double, y: Double, z: Double, order: String = js.native): Euler = js.native
  def copy(euler: Euler): Euler = js.native
  def reorder(newOrder: String): Euler = js.native
  def equals(euler: Euler): Boolean = js.native
  def fromArray(xyzo: js.Array[js.Any]): Euler = js.native
  def toArray: js.Array[js.Any] = js.native
  var onChange: js.Function0[Unit] = js.native
  override def clone(): Euler = js.native
}

