package physics.webapp.THREE.Camera

import org.scalajs.dom.Node
import org.scalajs.dom.raw.HTMLElement
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.THREE.Radians

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("THREE.OrbitControls")
class OrbitControls(var `object`: Camera, var _domElement: HTMLElement = js.native) extends js.Object {
  var autoRotate: Boolean = js.native
  var autoRotateSpeed: Double = js.native
  var dampingFactor: Double = js.native
  var enabled : Boolean = js.native
  var enableDamping: Boolean = js.native
  var enableKeys : Boolean = js.native
  var enablePan : Boolean = js.native
  var enableRotate : Boolean = js.native
  var enableZoom : Boolean = js.native
  var keyPanSpeed : Double = js.native
  var keys : Object = js.native
  var maxAzimuthAngle : Double = js.native
  var maxDistance : Double = js.native
  var maxPolarAngle : Double = js.native
  var maxZoom : Double = js.native
  var minAzimuthAngle : Double = js.native
  var minDistance : Double = js.native
  var minPolarAngle : Double = js.native
  var minZoom : Double = js.native
  var mouseButtons : Object = js.native
  var panSpeed : Double = js.native
  var position0 : Vector3 = js.native
  var rotateSpeed : Double = js.native
  var screenSpacePanning : Boolean = js.native
  var target0 : Vector3 = js.native
  var target : Vector3 = js.native
  var zoom0 : Double = js.native
  var zoomSpeed : Double = js.native


  def dispose () : Unit = js.native
  def getAzimuthalAngle () : Radians = js.native
  def getPolarAngle () : Radians = js.native
  def reset () : Unit = js.native
  def saveState () : Unit = js.native
  def update(): Unit = js.native
}

@js.native
@JSGlobal("THREE.TrackballControls")
class TrackballControls(var `object`: Camera, var domElement: Node = js.native) extends js.Object {

  var rotateSpeed: Double = js.native
  var zoomSpeed: Double = js.native
  var panSpeed: Double = js.native
  var noRotate: Boolean = js.native
  var noZoom: Boolean = js.native
  var noPan: Boolean = js.native
  var staticMoving: Boolean = js.native
  var dynamicDampingFactor: Double = js.native
  var minDistance: Double = js.native
  var maxDistance: Double = js.native

  def update(): Unit = js.native

}


