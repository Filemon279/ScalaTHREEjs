package physics.webapp


import com.filemon.threephysicscala.datgui.GUI

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import dom.document
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
    dom.document.body.appendChild(renderer.domElement)
    renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)

    val controls = new TrackballControls(camera, renderer.domElement)

    val gui = new GUI(js.Dynamic.literal(resizable= false, width=300))

    var verle: Int = 0
    var lineLenght: Int = 300

    var obj = js.Dynamic.literal(Density=0, EarthMass=earthMass, SunLargeMass=sunMass, SunSmallMass=smallSunMall, Sun3Mass=sun3Mass, Verle=0, LineLenght=lineLenght, DeltaT=DeltaT.deltaT)

    var density: Double = 0


    val earth = createPlanet(1, earthMass, "assets/images/textures/earthmap1k.jpg")
    earth.initialPosition(10, 20, 5)
    val sun = createPlanet(2, sunMass, "assets/images/textures/texture_sun.jpg")
    val sun2 = createPlanet(1, smallSunMall, "assets/images/textures/texture_sun.jpg")
    val sun3 = createPlanet(3, sun3Mass, "assets/images/textures/texture_sun.jpg")
    sun2.initialPosition(30, -10, 20)
    sun3.initialPosition(0, -70, -30)

    val onDensityChange: js.Any => js.Any = (arg: js.Any) => {density = arg.asInstanceOf[Double]}
    val onEarthMassChange: js.Any => js.Any = (arg: js.Any) => {earth.mass = arg.asInstanceOf[Double]}
    val onSunLargeMassChange: js.Any => js.Any = (arg: js.Any) => {sun.mass = arg.asInstanceOf[Double]}
    val onSunSmallMassChange: js.Any => js.Any = (arg: js.Any) => {sun2.mass = arg.asInstanceOf[Double]}
    val onSun3MassChange: js.Any => js.Any = (arg: js.Any) => {sun3.mass = arg.asInstanceOf[Double]}
    val onVerleChange: js.Any => js.Any = (arg: js.Any) => {verle = arg.asInstanceOf[Int]}
    val onLineLenghtChange: js.Any => js.Any = (arg: js.Any) => {lineLenght = arg.asInstanceOf[Int]}
    val onLocalDeltaChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.deltaT = arg.asInstanceOf[Double]}

    gui.add(obj, "Density").min(0).max(20).step(0.1).onChange(onDensityChange)
    gui.add(obj, "EarthMass").min(0).max(earthMass*2).step(0.1).onChange(onEarthMassChange)
    gui.add(obj, "SunLargeMass").min(0).max(sunMass*2).step(0.1).onChange(onSunLargeMassChange)
    gui.add(obj, "SunSmallMass").min(0).max(smallSunMall*2).step(0.1).onChange(onSunSmallMassChange)
    gui.add(obj, "Sun3Mass").min(-sun3Mass).max(sun3Mass*2).step(0.1).onChange(onSun3MassChange)
    gui.add(obj, "Verle").min(0).max(1).step(1).onChange(onVerleChange)
    gui.add(obj, "LineLenght").min(10).max(2500).step(1).onChange(onLineLenghtChange)
    gui.add(obj, "DeltaT").min(0.0009).max(0.1).step(0.00001).onChange(onLocalDeltaChange)


    controls.rotateSpeed = 2.0
    controls.zoomSpeed = 0.6
    controls.minDistance = 1
    controls.dynamicDampingFactor = 0.08

    val light = new AmbientLight(0xffffff, 2)

    camera.position.z = 50

    val starBackground = generateBackground(1000,  "assets/images/textures/galaxy_starfield7.jpg" )

    scene.add(sun.mesh)
    scene.add(sun2.mesh)
    scene.add(sun3.mesh)

    scene.add(earth.mesh)
    scene.add(light)
    scene.add(starBackground)

    val lineMaterial = new LineBasicMaterial(js.Dynamic.literal(color=0xffffff ));

    earth.velocity = new Vector3(20, 0, 10)

    earth.applyForce(Force.gravitationForce(sun, earth))
    earth.applyForce(Force.gravitationForce(sun2, earth))
    earth.applyForce(Force.gravitationForce(sun3, earth))
    earth.applyForce(Force.dragSphereForce(earth, density))
    earth.recalculateFirstEulerPosition()


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
      earth.addForce(Force.gravitationForce(sun, earth))
      earth.addForce(Force.gravitationForce(sun2, earth))
      earth.addForce(Force.gravitationForce(sun3, earth))
      if (verle==1) earth.recalculateVerletPosition()
      else {
        earth.applyForce(Force.gravitationForce(sun, earth))
        earth.applyForce(Force.gravitationForce(sun2, earth))
        earth.applyForce(Force.gravitationForce(sun3, earth))
        earth.applyForce(Force.dragSphereForce(earth, density))
        earth.recalculateEulerPosition()
      }
      addLine()
    }

    def addLine() = {
      scene.remove( line );
      var vertices: js.Array[Vector3] = geometry.vertices
      if(vertices.length > lineLenght){
        vertices.splice(0, 1)
      }
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


