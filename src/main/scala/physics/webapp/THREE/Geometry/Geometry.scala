package physics.webapp.THREE.Geometryimport
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.Objects.Object3D
import physics.webapp.THREE.THREE.Matrix4

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object Geometry {

  @js.native
  @JSGlobal("THREE.Geometry")
  class Geometry extends Object3D{
 //var boundingBox : Box3 = js.native
 //var boundingSphere : Sphere = js.native
 var colors : Array[_] = js.native
 var faces : Array[_] = js.native
 var faceVertexUvs : Array[_] = js.native
 var isGeometry : Boolean = js.native
 var lineDistances : Array[_] = js.native
 var morphTargets : Array[_] = js.native
 var morphNormals : Array[_] = js.native
 var skinWeights : Array[_] = js.native
 var skinIndices : Array[_] = js.native
 var vertices : js.Array[Vector3] = js.native
 var verticesNeedUpdate : Boolean = js.native
 var elementsNeedUpdate : Boolean = js.native
 var uvsNeedUpdate : Boolean = js.native
 var normalsNeedUpdate : Boolean = js.native
 var colorsNeedUpdate : Boolean = js.native
 var groupsNeedUpdate : Boolean = js.native
 var lineDistancesNeedUpdate : Boolean = js.native

  override def applyMatrix ( matrix : Matrix4 ) : Unit = js.native
  def center () : Geometry = js.native
  override def clone () : Geometry = js.native
  def computeBoundingBox () : Unit = js.native
  def computeBoundingSphere () : Unit = js.native
  def computeFaceNormals () : Unit = js.native
  def computeFlatVertexNormals () : Unit = js.native
  def computeMorphNormals () : Unit = js.native
  def computeVertexNormals ( areaWeighted : Boolean ) : Unit = js.native
  def copy ( geometry : Geometry ) : Geometry = js.native
  def dispose () : Unit = js.native
  def fromBufferGeometry ( geometry : BufferGeometry ) : Geometry = js.native
  override def lookAt ( vector : Vector3 )  = js.native
  def merge ( geometry : Geometry, matrix : Matrix4, materialIndexOffset : Integer ) : Unit = js.native
  def mergeMesh ( mesh : Mesh ) : Unit = js.native
  def mergeVertices () : Unit = js.native
  def normalize () : Unit = js.native
  def rotateX ( radians : Double ) : Geometry = js.native
  def rotateY ( radians : Double ) : Geometry = js.native
  def rotateZ ( radians : Double ) : Geometry = js.native
  def setFromPoints[T] ( points : Array[T] ) : Geometry = js.native
  def sortFacesByMaterialIndex ( ) : Unit = js.native
  def scale ( x : Double, y : Double, z : Double ) : Geometry = js.native
  def translate ( x : Double, y : Double, z : Double ) : Geometry = js.native
  //def toJSON ( ) : JSON = js.native
  }

  @js.native
  @JSGlobal("THREE.BufferGeometry")
  class BufferGeometry() extends Geometry {

  }

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
    def this(radius : Double, widthSegments : Integer = js.native, heightSegments : Integer = js.native, phiStart : Double = js.native, phiLength : Double = js.native, thetaStart : Double = js.native, thetaLength : Double = js.native) = this()
    var parameters: js.Any = js.native
  }

  @js.native
  @JSGlobal("THREE.PolyhedronGeometry")
  class PolyhedronGeometry(vertices: Array[Double], faces: Array[Int]) extends Geometry

  @js.native
  @JSGlobal("THREE.Mesh")
  class Mesh(geometry: Geometry, material: Material) extends Object3D

}
