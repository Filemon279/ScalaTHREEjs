package physics.webapp.THREE.Camera

import physics.webapp.THREE.Math.Vector3

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal


@JSGlobal("THREE.Camera")
abstract class Camera extends js.Object {
  def aspect: Number
}

@JSGlobal("THREE.PerspectiveCamera")
class PerspectiveCamera(fov: Number, _aspect: Number, near: Number, far: Number) extends Camera {
  var position: Vector3 = _
  var aspect = _aspect
  def updateProjectionMatrix() = js.native
}

