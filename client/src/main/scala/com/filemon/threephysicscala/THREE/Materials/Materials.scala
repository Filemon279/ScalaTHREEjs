package physics.webapp.THREE.Materials

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal



class Material extends js.Object

@js.native
@JSGlobal("THREE.MeshBasicMaterial")
class MeshBasicMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.MeshStandardMaterial")
class MeshStandardMaterial(parameters: js.Dynamic) extends Material

@js.native
@JSGlobal("THREE.LineBasicMaterial")
class LineBasicMaterial(parameters: js.Dynamic) extends Material


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