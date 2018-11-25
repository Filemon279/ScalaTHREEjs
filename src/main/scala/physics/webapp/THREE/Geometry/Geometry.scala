package physics.webapp.THREE.Geometryimport
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Objects.Object3D

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object Geometry {

  @js.native
  @JSGlobal("THREE.Geometry")
  class Geometry extends Object3D

  @js.native
  @JSGlobal("THREE.BoxGeometry")
  class BoxGeometry  extends Geometry {
    def this(width: Double, height: Double, depth: Double, widthSegments: Double = js.native, heightSegments: Double = js.native, depthSegments: Double = js.native) = this()
    var parameters: js.Any = js.native
    var widthSegments: Double = js.native
    var heightSegments: Double = js.native
    var depthSegments: Double = js.native
  }

  @js.native
  @JSGlobal("THREE.SphereGeometry")
  class SphereGeometry extends Geometry {
    def this(radius : Float, widthSegments : Integer = js.native, heightSegments : Integer = js.native, phiStart : Float = js.native, phiLength : Float = js.native, thetaStart : Float = js.native, thetaLength : Float = js.native) = this()
    var parameters: js.Any = js.native
  }

  @js.native
  @JSGlobal("THREE.PolyhedronGeometry")
  class PolyhedronGeometry(vertices: Array[Float], faces: Array[Int]) extends Geometry

  @js.native
  @JSGlobal("THREE.Mesh")
  class Mesh(geometry: Geometry, material: Material) extends Object3D

}
