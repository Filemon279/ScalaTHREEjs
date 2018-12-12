package com.filemon.threephysicscala

import com.filemon.threephysicscala.datgui.GUI
import org.scalajs.dom
import physics.webapp.Physics.DeltaT
import physics.webapp.Physics.Spring
import physics.webapp.Physics.Force
import physics.webapp.ScalaProject._
import physics.webapp.THREE.Camera.{PerspectiveCamera, TrackballControls}
import physics.webapp.THREE.Geometryimport.Geometry.Geometry
import physics.webapp.THREE.Lights.AmbientLight
import physics.webapp.THREE.Materials.LineBasicMaterial
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.Objects.{Line, Scene, WebGLRenderer}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

@JSExportTopLevel("physics.webapp.SpringSimulation")
@JSExportAll
object SpringSimulation {

  def init(): Unit = {

    val scene = new Scene()
    val camera = new PerspectiveCamera(75, dom.window.innerWidth / dom.window.innerHeight, 0.1, 90000)
    val renderer = new WebGLRenderer(js.Dynamic.literal(antialias=true))
    dom.document.body.appendChild(renderer.domElement)
    renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)

    val controls = new TrackballControls(camera, renderer.domElement)
    val gui = new GUI(js.Dynamic.literal(resizable= false, width=300))

    var obj = js.Dynamic.literal(DeltaT=DeltaT.deltaT)

    val onLocalDeltaChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.deltaT = arg.asInstanceOf[Double]}

    gui.add(obj, "DeltaT").min(0.0009).max(2.2).step(0.00001).onChange(onLocalDeltaChange)

    controls.rotateSpeed = 2.0
    controls.zoomSpeed = 0.6
    controls.minDistance = 1
    controls.dynamicDampingFactor = 0.08

    camera.position.z = 50

    val light = new AmbientLight(0xffffff, 2)
    val starBackground = generateBackground(1000,  "assets/images/textures/galaxy_starfield7.jpg" )
    scene.add(light)
    scene.add(starBackground)



    val earth = createPlanet(1, earthMass, "assets/images/textures/earthmap1k.jpg")
    earth.initialPosition(45, 0, 0)
    val sun = createPlanet(2, sunMass, "assets/images/textures/texture_sun.jpg")
    sun.initialPosition(0, 0, 0)
    scene.add(earth.mesh)
    scene.add(sun.mesh)

    earth.velocity = new Vector3(4, 0, 0)
    earth.recalculateFirstEulerPosition()

    val lineMaterial = new LineBasicMaterial(js.Dynamic.literal(color=0xffffff ));
    var geometry = new Geometry()
    geometry.vertices.push(earth.position(), sun.position())
    var line = new Line( geometry, lineMaterial )

    def renderLoop(timestamp: Double): Unit = {
      dom.window.requestAnimationFrame(renderLoop _)
      controls.update()
      renderer.render(scene, camera)
      earth.resetForce()
      earth.addForce(Spring.springForce(earth, sun, 1, 30))
      println(earth.force.x+" "+earth.force.y+" "+earth.force.z)
      earth.recalculateVerletPosition()
      addLine();
    }



    def addLine() = {
      scene.remove( line );
      geometry = new Geometry()
      geometry.vertices.push(earth.position(), sun.position())
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
