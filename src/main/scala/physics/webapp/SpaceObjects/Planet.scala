package physics.webapp.SpaceObjects

import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh}
import physics.webapp.THREE.Materials.Material
import physics.webapp.THREE.Math.Vector3

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel, JSGlobal, ScalaJSDefined}

trait PlanetParams {
  def mass: Double
  def radious: Double
  def velocity: Vector3
}

@JSExport
class Planet(geometry: Geometry, material: Material, _mass: Double, _radious: Double) extends PlanetParams {
  var mesh = new Mesh(geometry, material)
  var mass = _mass
  var radious = _radious
  var velocity = new Vector3(0,0,0)
}
