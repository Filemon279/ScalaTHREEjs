package physics.webapp.THREE.Lights

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

abstract class Light extends js.Object {
  def color: Integer = js.native
  def intensity: Float = js.native
  def isLight: Boolean = js.native

  def copy(source : Light): Light = js.native

}

@js.native
@JSGlobal("THREE.PointLight")
class PointLight(color: Integer = js.native, intensity: Float = js.native, _distance : Number = js.native, _decay : Float = js.native) extends Light {
  var decay : Float = js.native
  var distance : Float = js.native
  var isPointLight : Boolean = js.native
  var power : Float = js.native
  //var shadow : LightShadow = js.native

}
