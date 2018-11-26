package physics.webapp.THREE.Camera

import physics.webapp.THREE.Math.Vector3

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("THREE.Camera")
abstract class Camera extends js.Object {
  def aspect: Number
}

@js.native
@JSGlobal("THREE.PerspectiveCamera")
class PerspectiveCamera(var fov: Number, var aspect: Number, var near: Number, var far: Number) extends Camera {
  var position: Vector3 = js.native
  def updateProjectionMatrix() = js.native
}

