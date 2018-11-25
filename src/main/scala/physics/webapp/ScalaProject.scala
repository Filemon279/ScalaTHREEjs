package physics.webapp

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import dom.document
import org.querki.jquery._
import physics.webapp.Environment.{Background, Planets}
import physics.webapp.Physics._
import physics.webapp.Physicsimport.Force
import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Camera.{OrbitControls, PerspectiveCamera, TrackballControls}
import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh, SphereGeometry}
import physics.webapp.THREE.Lights.{AmbientLight, PointLight}
import physics.webapp.THREE.Materials.{LineBasicMaterial, MeshStandardMaterial, Texture, TextureLoader}
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.Objects.{Line, Scene, WebGLRenderer}

import scala.scalajs.js

object ScalaProject extends Constants with Planets with Background {
  def main(args: Array[String]): Unit = {
    createTestCube()
  }

  def createTestCube(): Unit = {

    val scene = new Scene()
    val camera = new PerspectiveCamera(75, dom.window.innerWidth / dom.window.innerHeight, 0.1, 90000)
    val renderer = new WebGLRenderer(js.Dynamic.literal(antialias=true))
    val controls = new TrackballControls(camera)
    controls.rotateSpeed = 2.0
    controls.zoomSpeed = 0.6
    controls.minDistance = 1
    controls.dynamicDampingFactor = 0.08
    val light = new AmbientLight(0xffffff, 2)

    dom.document.body.appendChild(renderer.domElement)
    renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)
    camera.position.z = 50

    val earth = createPlanet(1, earthMass, "./textures/earthmap1k.jpg")
    earth.initialPosition(10, 20, 5)
    val sun = createPlanet(2, sunMass, "./textures/texture_sun.jpg")
    val sun2 = createPlanet(1, sunMass, "./textures/texture_sun.jpg")
    sun2.initialPosition(30, 10, 20)

    val starBackground = generateBackground(1000, "./textures/galaxy_starfield7.jpg" )

    scene.add(sun.mesh)
    scene.add(sun2.mesh)

    scene.add(earth.mesh)
    scene.add(light)
    scene.add(starBackground)

    val lineMaterial = new LineBasicMaterial(js.Dynamic.literal(color=0xffffff ));



    earth.velocity = new Vector3(20, 0, 10)

    var geometry = new Geometry()
    var line = new Line( geometry, lineMaterial )

    def renderLoop(timestamp: Double): Unit = {
      dom.window.requestAnimationFrame(renderLoop _)

      sun.mesh.rotation.x += .005
      sun.mesh.rotation.y += .005
      earth.mesh.rotation.x -= .005
      earth.mesh.rotation.y += .005
      controls.update()
      renderer.render(scene, camera)
      earth.applyForce(Force.gravitationForce(sun, earth))
      earth.applyForce(Force.gravitationForce(sun2, earth))
      earth.recalculatePosition()
      addLine()
    }

    def addLine() = {
      scene.remove( line );
      var vertices: js.Array[Vector3] = geometry.vertices
      vertices.push(earth.position());
      geometry = new Geometry()
      geometry.vertices = vertices;
      line = new Line( geometry, lineMaterial )
      scene.add( line );
      line.updateMatrix();
    }

    renderLoop(System.currentTimeMillis())

    dom.window.addEventListener( "resize", (onWindowResize _), false)

    def onWindowResize[T](arg: T) = {
      camera.aspect = dom.window.innerWidth / dom.window.innerHeight
      camera.updateProjectionMatrix()
      renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)
    }

  }
}


