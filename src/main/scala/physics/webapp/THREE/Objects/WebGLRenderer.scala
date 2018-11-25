package physics.webapp.THREE.Objects
import physics.webapp.THREE.Camera.Camera
import org.scalajs.dom.Node
import org.scalajs.dom.raw.HTMLElement
import physics.webapp.THREE.Materials.Texture
import physics.webapp.THREE.Math.Vector2

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

  @js.native
  @JSGlobal("THREE.WebGLRenderer")
  class WebGLRenderer(params: js.Dynamic) extends js.Object {

  var autoClear : Boolean = js.native
  var autoClearColor : Boolean = js.native
  var autoClearDepth : Boolean = js.native
  var autoClearStencil : Boolean = js.native
  var capabilities : Object = js.native
  //var clippingPlanes : js.Array = js.native
  //var context : WebGLRenderingContext = js.native
  var domElement : Node = js.native
  var extensions : Object = js.native
  var gammaFactor : Float = js.native
  var gammaInput : Boolean = js.native
  var gammaOutput : Boolean = js.native
  var info : Object = js.native
  var localClippingEnabled : Boolean = js.native
  var maxMorphTargets : Integer = js.native
  var maxMorphNormals : Integer = js.native
  var physicallyCorrectLights : Boolean = js.native
  var properties : Object = js.native
  //var renderLists : WebGLRenderLists = js.native
  //var shadowMap : WebGLShadowMap = js.native
  //var shadowMap.enabled : Boolean = js.native
  //var shadowMap.autoUpdate : Boolean = js.native
  //var shadowMap.needsUpdate : Boolean = js.native
  //var shadowMap.type : Integer = js.native*/
  var sortObjects : Boolean = js.native
  var state : Object = js.native
  //var toneMapping : Constant = js.native
  var toneMappingExposure : Number = js.native
  var toneMappingWhitePoint : Number = js.native


  def allocTextureUnit : Integer = js.native
  def clear ( color : Boolean, depth : Boolean, stencil : Boolean ) : Unit = js.native
  def clearColor ( ) : Unit = js.native
  def clearDepth ( ) : Unit = js.native
  def clearStencil ( ) : Unit = js.native
  def compile ( scene : Scene, camera : Camera ) : Unit = js.native
  def copyFramebufferToTexture ( position : Vector2, texture : Texture, level : Number ) : Unit = js.native
  def copyTextureToTexture ( position : Vector2, srcTexture : Texture, dstTexture : Texture, level : Number ) : Unit = js.native
  def dispose ( ) : Unit = js.native
  //def extensions.get ( extensionName : String ) : Object = js.native
  def forceContextLoss ( ) : Unit = js.native
  def getClearAlpha () : Float = js.native
  //def getClearColor () : Color = js.native
  //def getContext () : WebGLRenderingContext = js.native
  //def getContextAttributes () : WebGLContextAttributes = js.native
  //def getRenderTarget () : RenderTarget = js.native
  //def getCurrentViewport () : RenderTarget = js.native
  def getDrawingBufferSize () : Object = js.native
  def getPixelRatio () : Number = js.native
  def getSize () : Object = js.native
  def resetGLState ( ) : Unit = js.native
  //def readRenderTargetPixels ( renderTarget : WebGLRenderTarget, x : Float, y : Float, width : Float, height : Float, buffer ) : Unit = js.native
  def render ( scene : Scene, camera : Camera, renderTarget : WebGLRenderTarget = js.native, forceClear : Boolean = js.native ) : Unit = js.native
  //def renderBufferDirect ( camera : Camera, fog : Fog, geometry : Geometry, material : Material, object : Object3D, group : Object ) : Unit = js.native
  //def renderBufferImmediate ( object : Object3D, program : shaderprogram, shading : Material ) : Unit = js.native
  def setAnimationLoop ( callback : Unit ) : Unit = js.native
  def setClearAlpha ( alpha : Float ) : Unit = js.native
  def setClearColor ( color : Color, alpha : Float ) : Unit = js.native
  def setPixelRatio ( value : Double ) : Unit = js.native
  def setRenderTarget ( renderTarget : WebGLRenderTarget ) : Unit = js.native
  def setScissor ( x : Integer, y : Integer, width : Integer, height : Integer ) : Unit = js.native
  def setScissorTest ( boolean : Boolean ) : Unit = js.native
  def setSize ( width : Double, height : Double, updateStyle : Boolean = js.native ) : Unit = js.native
  def setTexture2D ( texture : Texture, slot : Double ) : Unit = js.native
  //def setTextureCube ( cubeTexture : CubeTexture, slot : Number ) : Unit = js.native
  def setViewport ( x : Integer, y : Integer, width : Integer, height : Integer ) : Unit = js.native

  }
