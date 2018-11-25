package physics.webapp.THREE

import physics.webapp.THREE.Objects.WebGLRenderer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object THREE {

  class Layers()

  @js.native
  @JSGlobal("Matrix3")
  class Matrix3() extends js.Object

  @js.native
  @JSGlobal("Matrix4")
  class Matrix4() extends js.Object

  @js.native
  @JSGlobal("THREE.Raycaster")
  class Raycaster() extends js.Object

  @js.native
  @JSGlobal("THREE.Quaternion")
  class Quaternion(var x: Float, var y: Float, var z: Float, var w: Float) extends js.Object

  @js.native
  @JSGlobal("THREE.radians")
  class Radians(var value: Number) extends js.Object

  @js.native
  @JSGlobal("THREE.SimpleWebGLRenderer")
  class SimpleWebGLRenderer() extends WebGLRenderer(null)

  @js.native
  @JSGlobal("THREE.Clock")
  class Clock extends js.Object


}
