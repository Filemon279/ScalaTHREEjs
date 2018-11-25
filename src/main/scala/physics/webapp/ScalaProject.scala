package physics.webapp

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import dom.document
import org.querki.jquery._
import physics.webapp.Environment.{Background, Planets}
import physics.webapp.Physics._
import physics.webapp.Physicsimport.Force
import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Camera.{OrbitControls, PerspectiveCamera}
import physics.webapp.THREE.Geometryimport.Geometry.{Mesh, SphereGeometry}
import physics.webapp.THREE.Lights.{AmbientLight, PointLight}
import physics.webapp.THREE.Materials.{MeshStandardMaterial, Texture, TextureLoader}
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.Objects.{Scene, WebGLRenderer}

import scala.scalajs.js

object ScalaProject extends Constants with Planets with Background {
  def main(args: Array[String]): Unit = {
    createTestCube()
  }

  def createTestCube(): Unit = {

    val scene = new Scene()
    val camera = new PerspectiveCamera(75, dom.window.innerWidth / dom.window.innerHeight, 0.1, 90000)
    val renderer = new WebGLRenderer(js.Dynamic.literal(antialias=true))
    val controls = new OrbitControls(camera)
    val light = new AmbientLight(0xffffff, 2)

    dom.document.body.appendChild(renderer.domElement)
    renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)
    camera.position.z = 20

    val earth = createPlanet(1, earthMass, "./textures/earthmap1k.jpg")
    val sun = createPlanet(2, sunMass, "./textures/texture_sun.jpg")
    val starBackground = generateBackground(1000, "./textures/galaxy_starfield7.jpg" )

    scene.add(sun.mesh)
    scene.add(earth.mesh)
    scene.add(light)

    val textureLoader = new TextureLoader()
    val texture: Texture = textureLoader.load("./textures/galaxy_starfield7.jpg")
    scene.background = texture
    //scene.add(starBackground)


    earth.mesh.position.x = 5
    earth.velocity = new Vector3(10, 10, 0)

    def renderLoop(timestamp: Double): Unit = {
      dom.window.requestAnimationFrame(renderLoop _)
      sun.mesh.rotation.x += .005
      sun.mesh.rotation.y += .005
      earth.mesh.rotation.x -= .005
      earth.mesh.rotation.y += .005
      controls.update()
      renderer.render(scene, camera)

      earth.velocity = Velocity.velocity(earth.velocity, Force.gravitationForce(sun, earth))
      earth.mesh.position.x = earth.mesh.position.x + (earth.velocity.x * deltaT)
      earth.mesh.position.y = earth.mesh.position.y + (earth.velocity.y * deltaT)
      earth.mesh.position.z = earth.mesh.position.z + (earth.velocity.z * deltaT)
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


