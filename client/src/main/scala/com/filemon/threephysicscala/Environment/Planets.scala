package physics.webapp.Environment

import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Geometryimport.Geometry.SphereGeometry
import physics.webapp.THREE.Materials.{MeshStandardMaterial, Texture, TextureLoader}

import scala.scalajs.js

trait Planets extends Constants {

    def createPlanet(r: Float, planetMass: Double, textureUrl: String, wSeg: Int = sphereSegments, hSeg: Int = sphereSegments): Planet = {
      val geometry = new SphereGeometry(r, wSeg, hSeg)
      val loader = new TextureLoader()
      val texture: Texture = loader.load(textureUrl)
      val material = new MeshStandardMaterial(js.Dynamic.literal(map = texture))
      val sphere = new Planet(geometry, material, planetMass, r)
      sphere
    }


}
