package physics.webapp.Environment
import physics.webapp.THREE.Geometryimport.Geometry.{Mesh, SphereGeometry}
import physics.webapp.THREE.Materials._

import scala.scalajs.js

trait Background {

  def generateBackground(radius: Int, textureUrl: String, wSeg: Int = 64, hSeg: Int = 64) = {
    val sphere = new SphereGeometry(radius, wSeg, hSeg)
    val textureLoader = new TextureLoader()
    val texture: Texture = textureLoader.load(textureUrl)
    val material = new MeshPhongMaterial( js.Dynamic.literal(map=texture, wireframe= false, side = DoubleSide, flatShading = true) );
   // val material = new MeshBasicMaterial(js.Dynamic.literal(map=texture, depthTest=0, side=BackSide, transparent=true, blending=AdditiveBlending ))
    new Mesh(sphere, material)
  }
}
