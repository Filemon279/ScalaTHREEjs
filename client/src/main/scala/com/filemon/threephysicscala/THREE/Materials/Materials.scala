package physics.webapp.THREE.Materials

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal



class Material extends js.Object {
  var alphaTest : Float = js.native
  var blendDst : Integer = js.native
  var blendDstAlpha : Integer = js.native
  var blendEquation : Integer = js.native
  var blendEquationAlpha : Integer = js.native
  //var blending : Blending = js.native
  var blendSrc : Integer = js.native
  var blendSrcAlpha : Integer = js.native
  var clipIntersection : Boolean = js.native
  //var clippingPlanes : js.Array = js.native
  var clipShadows : Boolean = js.native
  var colorWrite : Boolean = js.native
  var customDepthMaterial : Material = js.native
  var customDistanceMaterial : Material = js.native
  var defines : js.Object = js.native
  var depthFunc : Integer = js.native
  var depthTest : Boolean = js.native
  var depthWrite : Boolean = js.native
  var flatShading : Boolean = js.native
  var fog : Boolean = js.native
  var id : Integer = js.native
  var isMaterial : Boolean = js.native
  var lights : Boolean = js.native
  var name : String = js.native
  var needsUpdate : Boolean = js.native
  var opacity : Float = js.native
  var polygonOffset : Boolean = js.native
  var polygonOffsetFactor : Integer = js.native
  var polygonOffsetUnits : Integer = js.native
  var precision : String = js.native
  var premultipliedAlpha : Boolean = js.native
  var dithering : Boolean = js.native
  var shadowSide : Integer = js.native
  var side : Integer = js.native
  var transparent : Boolean = js.native
  var `type` : String = js.native
  var uuid : String = js.native
  var vertexColors : Integer = js.native
  var visible : Boolean = js.native
  var userData : js.Object = js.native

  var size: Double = js.native
}

@js.native
@JSGlobal("THREE.MeshBasicMaterial")
class MeshBasicMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.MeshStandardMaterial")
class MeshStandardMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.LineBasicMaterial")
class LineBasicMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.PointCloudMaterial")
class PointCloudMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.PointsMaterial")
class PointsMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.ShaderMaterial")
class ShaderMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.MeshShaderMaterial")
class MeshShaderMaterial(parameters: js.Dynamic) extends Material

//////////////////////////
//    Side
//////////////////////////


@js.native
@JSGlobal("THREE.BackSide")
object BackSide extends js.Object

@js.native
@JSGlobal("THREE.FrontSide")
object FrontSide extends js.Object

@js.native
@JSGlobal("THREE.FrontSide")
object DoubleSide extends js.Object


//////////////////////////
//    Colors
//////////////////////////


@js.native
@JSGlobal("THREE.NoColors")
object NoColors extends js.Object

@js.native
@JSGlobal("THREE.FaceColors")
object FaceColors extends js.Object

@js.native
@JSGlobal("THREE.VertexColors")
object VertexColors extends js.Object


//////////////////////////
//   Blending
//////////////////////////

@js.native
@JSGlobal("THREE.NoBlending")
object NoBlending extends js.Object

@js.native
@JSGlobal("THREE.NormalBlending")
object NormalBlending extends js.Object

@js.native
@JSGlobal("THREE.AdditiveBlending")
object AdditiveBlending extends js.Object

@js.native
@JSGlobal("THREE.SubtractiveBlending")
object SubtractiveBlending extends js.Object

@js.native
@JSGlobal("THREE.MultiplyBlending")
object MultiplyBlending extends js.Object

@js.native
@JSGlobal("THREE.CustomBlending")
object CustomBlending extends js.Object


//////////////////////////
//   Depth Mode
//////////////////////////


@js.native
@JSGlobal("THREE.NeverDepth")
object NeverDepth extends js.Object

@js.native
@JSGlobal("THREE.AlwaysDepth")
object AlwaysDepth extends js.Object

@js.native
@JSGlobal("THREE.LessDepth")
object LessDepth extends js.Object

@js.native
@JSGlobal("THREE.LessEqualDepth")
object LessEqualDepth extends js.Object

@js.native
@JSGlobal("THREE.GreaterEqualDepth")
object GreaterEqualDepth extends js.Object

@js.native
@JSGlobal("THREE.GreaterDepth")
object GreaterDepth extends js.Object

@js.native
@JSGlobal("THREE.NotEqualDepth")
object NotEqualDepth extends js.Object

//////////////////////////
//  Texture Combine Operations
//////////////////////////

@js.native
@JSGlobal("THREE.MultiplyOperation")
object MultiplyOperation extends js.Object

@js.native
@JSGlobal("THREE.MixOperation")
object MixOperation extends js.Object

@js.native
@JSGlobal("THREE.AddOperation")
object AddOperation extends js.Object