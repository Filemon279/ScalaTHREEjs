package physics.webapp.THREE.Objects

import physics.webapp.THREE.Geometryimport.Geometry.Geometry
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.THREE._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName}

@js.native
trait WebGLRenderTarget extends js.Object

@JSName("THREE.Color")
class Color(r : String, g : Float = js.native, b : Float =  js.native) extends js.Object

@JSName("THREE.Line")
class Line(var geometry : Geometry, var material : Material) extends Object3D {

 var isLine : Boolean = js.native

 def computeLineDistances () : Line = js.native
 def raycast[T] ( raycaster : Raycaster, intersects : Array[T] ) : Unit = js.native
 override def clone () : Line = js.native

}




@js.native
@JSGlobal("THREE.Object3D")
class Object3D() extends js.Object {
var castShadow : Boolean = js.native
var children : Object3D = js.native
var frustumCulled : Boolean = js.native
var id : Integer = js.native
var isObject3D : Boolean = js.native
var layers : Layers = js.native
var matrix : Matrix4 = js.native
var matrixAutoUpdate : Boolean = js.native
var matrixWorld : Matrix4 = js.native
var matrixWorldNeedsUpdate : Boolean = js.native
var modelViewMatrix : Matrix4 = js.native
var name : String = js.native
var normalMatrix : Matrix3 = js.native
var onAfterRender : Unit = js.native
var onBeforeRender : Unit = js.native
var parent : Object3D = js.native
var position : Vector3 = js.native
var quaternion : Quaternion = js.native
var receiveShadow : Boolean = js.native
var renderOrder : Number = js.native
var rotation : Euler = js.native
var scale : Vector3 = js.native
var up : Vector3 = js.native
var userData : js.Object = js.native
var uuid : String = js.native
var visible : Boolean = js.native

 def add ( `object` : Object3D) : Unit = js.native
 def applyMatrix ( matrix : Matrix4 ) : Unit = js.native
 def applyQuaternion ( quaternion : Quaternion ) : Object3D = js.native
 def clone ( recursive : Boolean ) : Object3D = js.native
 def copy ( `object` : Object3D, recursive : Boolean ) : Object3D = js.native
 def getObjectById ( id : Integer ) : Object3D = js.native
 def getObjectByName ( name : String ) : Object3D = js.native
 def getObjectByProperty ( name : String, value : Float ) : Object3D = js.native
 def getWorldPosition ( target : Vector3 ) : Vector3 = js.native
 def getWorldQuaternion ( target : Quaternion ) : Quaternion = js.native
 def getWorldScale ( target : Vector3 ) : Vector3 = js.native
 def getWorldDirection ( target : Vector3 ) : Vector3 = js.native
 def localToWorld ( vector : Vector3 ) : Vector3 = js.native
 def lookAt ( vector : Vector3 ) : Unit = js.native
 def lookAt ( x : Float, y : Float, z : Float ) : Unit = js.native
 def raycast[T] ( raycaster : Raycaster, intersects : js.Array[T] ) : Array[T] = js.native
 def remove ( `object` : Object3D) : Unit = js.native
 def rotateOnAxis ( axis : Vector3, angle : Float ) : Object3D = js.native
 def rotateOnWorldAxis ( axis : Vector3, angle : Float ) : Object3D = js.native
 def rotateX ( rad : Float ) : Object3D = js.native
 def rotateY ( rad : Float ) : Object3D = js.native
 def rotateZ ( rad : Float ) : Object3D = js.native
 def setRotationFromAxisAngle ( axis : Vector3, angle : Float ) : Unit = js.native
 def setRotationFromEuler ( euler : Euler ) : Unit = js.native
 def setRotationFromMatrix ( m : Matrix4 ) : Unit = js.native
 def setRotationFromQuaternion ( q : Quaternion ) : Unit = js.native
 def toJSON ( q : Quaternion ) : Unit = js.native
 def translateOnAxis ( axis : Vector3, distance : Float ) : Object3D = js.native
 def translateX ( distance : Float ) : Object3D = js.native
 def translateY ( distance : Float ) : Object3D = js.native
 def translateZ ( distance : Float ) : Object3D = js.native
 def traverse ( callback : Unit ) : Unit = js.native
 def traverseVisible ( callback : Unit ) : Unit = js.native
 def traverseAncestors ( callback : Unit ) : Unit = js.native
 def updateMatrix () : Unit = js.native
 def updateMatrixWorld ( force : Boolean ) : Unit = js.native
 def worldToLocal ( vector : Vector3 ) : Vector3 = js.native

}

