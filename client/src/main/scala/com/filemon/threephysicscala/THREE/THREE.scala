package physics.webapp.THREE

import physics.webapp.THREE.Camera.Camera
import physics.webapp.THREE.Math.{Vector2, Vector3}
import physics.webapp.THREE.Objects.WebGLRenderer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object THREE {

  class Layers()



  @js.native
  @JSGlobal("THREE.Face3")
  class Face3( a : Integer = js.native , b : Integer = js.native , c : Integer = js.native , normal : Vector3 = js.native , color : Int = js.native , materialIndex : Integer = js.native ) extends js.Object

  @js.native
  @JSGlobal("Matrix3")
  class Matrix3() extends js.Object

  @js.native
  @JSGlobal("Matrix4")
  class Matrix4() extends js.Object

  @js.native
  @JSGlobal("THREE.Raycaster")
  class Raycaster( origin : Vector3, direction : Vector3, near: Double = js.native , far: Double = js.native ) extends js.Object{

    def setFromCamera ( coords : Vector2, camera : Camera ) : Unit = js.native
  }

  @js.native
  @JSGlobal("THREE.Quaternion")
  class Quaternion(var x: Double, var y: Double, var z: Double, var w: Double) extends js.Object

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
