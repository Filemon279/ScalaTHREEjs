package physics.webapp.THREE.Materials

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}


class Material extends js.Object

@JSGlobal("THREE.MeshBasicMaterial")
class MeshBasicMaterial(parameters: js.Dynamic) extends Material

@JSGlobal("THREE.MeshStandardMaterial")
class MeshStandardMaterial(parameters: js.Dynamic) extends Material

@JSGlobal("THREE.LineBasicMaterial")
class LineBasicMaterial(parameters: js.Dynamic) extends Material


//////////////////////////
//    Side
//////////////////////////


@JSName("THREE.BackSide")
object BackSide extends js.Object

@JSName("THREE.FrontSide")
object FrontSide extends js.Object

@JSName("THREE.FrontSide")
object DoubleSide extends js.Object


//////////////////////////
//    Colors
//////////////////////////


@JSName("THREE.NoColors")
object NoColors extends js.Object

@JSName("THREE.FaceColors")
object FaceColors extends js.Object

@JSName("THREE.VertexColors")
object VertexColors extends js.Object


//////////////////////////
//   Blending
//////////////////////////

@JSName("THREE.NoBlending")
object NoBlending extends js.Object

@JSName("THREE.NormalBlending")
object NormalBlending extends js.Object

@JSName("THREE.AdditiveBlending")
object AdditiveBlending extends js.Object

@JSName("THREE.SubtractiveBlending")
object SubtractiveBlending extends js.Object

@JSName("THREE.MultiplyBlending")
object MultiplyBlending extends js.Object

@JSName("THREE.CustomBlending")
object CustomBlending extends js.Object


//////////////////////////
//   Depth Mode
//////////////////////////


@JSName("THREE.NeverDepth")
object NeverDepth extends js.Object

@JSName("THREE.AlwaysDepth")
object AlwaysDepth extends js.Object

@JSName("THREE.LessDepth")
object LessDepth extends js.Object

@JSName("THREE.LessEqualDepth")
object LessEqualDepth extends js.Object

@JSName("THREE.GreaterEqualDepth")
object GreaterEqualDepth extends js.Object

@JSName("THREE.GreaterDepth")
object GreaterDepth extends js.Object

@JSName("THREE.NotEqualDepth")
object NotEqualDepth extends js.Object

//////////////////////////
//  Texture Combine Operations
//////////////////////////

@JSName("THREE.MultiplyOperation")
object MultiplyOperation extends js.Object

@JSName("THREE.MixOperation")
object MixOperation extends js.Object

@JSName("THREE.AddOperation")
object AddOperation extends js.Object