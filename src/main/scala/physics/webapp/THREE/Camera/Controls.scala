package physics.webapp.THREE.Camera

import org.scalajs.dom.raw.HTMLElement
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.THREE.Radians

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("THREE.OrbitControls")
class OrbitControls(`_object`: Camera, _domElement: HTMLElement = js.native) extends js.Object {
  var domElement: HTMLElement = _domElement
  var `object`: Camera = `_object`

  var autoRotate: Boolean = js.native
  var autoRotateSpeed: Float = js.native
  var dampingFactor: Float = js.native
  var enabled : Boolean = js.native
  var enableDamping: Boolean = js.native
  var enableKeys : Boolean = js.native
  var enablePan : Boolean = js.native
  var enableRotate : Boolean = js.native
  var enableZoom : Boolean = js.native
  var keyPanSpeed : Float = js.native
  var keys : Object = js.native
  var maxAzimuthAngle : Float = js.native
  var maxDistance : Float = js.native
  var maxPolarAngle : Float = js.native
  var maxZoom : Float = js.native
  var minAzimuthAngle : Float = js.native
  var minDistance : Float = js.native
  var minPolarAngle : Float = js.native
  var minZoom : Float = js.native
  var mouseButtons : Object = js.native
  var panSpeed : Float = js.native
  var position0 : Vector3 = js.native
  var rotateSpeed : Float = js.native
  var screenSpacePanning : Boolean = js.native
  var target0 : Vector3 = js.native
  var target : Vector3 = js.native
  var zoom0 : Float = js.native
  var zoomSpeed : Float = js.native

  def dispose () : Unit = js.native
  def getAzimuthalAngle () : Radians = js.native
  def getPolarAngle () : Radians = js.native
  def reset () : Unit = js.native
  def saveState () : Unit = js.native
  def update(): Unit = js.native
}
