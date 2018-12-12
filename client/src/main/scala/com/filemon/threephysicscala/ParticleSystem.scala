package com.filemon.threephysicscala

import com.filemon.threephysicscala.SpaceObjects.Particle
import com.filemon.threephysicscala.datgui.GUI
import org.scalajs.dom
import physics.webapp.Physics.DeltaT
import physics.webapp.Physics.Spring
import physics.webapp.Physics.Force
import physics.webapp.ScalaProject._
import physics.webapp.THREE.Camera.{PerspectiveCamera, TrackballControls}
import physics.webapp.THREE.Geometryimport.Geometry._
import physics.webapp.THREE.Lights.AmbientLight
import physics.webapp.THREE.Materials._
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.Objects.{Line, Scene, WebGLRenderer}
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}
import scala.util.Random
import org.querki.jquery._

@JSExportTopLevel("physics.webapp.ParticleSystem")
@JSExportAll
object ParticleSystem {

  def init(): Unit = {

    val scene = new Scene()
    val camera = new PerspectiveCamera(75, dom.window.innerWidth / dom.window.innerHeight, 0.1, 10000)
    val renderer = new WebGLRenderer(js.Dynamic.literal(antialias=true))
    dom.document.body.appendChild(renderer.domElement)
    renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)

    val controls = new TrackballControls(camera, renderer.domElement)

    controls.rotateSpeed = 2.0
    controls.zoomSpeed = 0.6
    controls.minDistance = 1
    controls.dynamicDampingFactor = 0.08
    controls.maxDistance = 500

    camera.position.z = 150

    var mousePos = new Vector3(0,0,0); // create once and reuse

    val light = new AmbientLight(0xffffff, 2)
    val starBackground = generateBackground(1000,  "assets/images/textures/galaxy_starfield7.jpg" )
    scene.add(light)
    scene.add(starBackground)

    DeltaT.deltaT = 0.4

    val gui = new GUI(js.Dynamic.literal(resizable= false, width=300))
    var obj = js.Dynamic.literal(DeltaT=DeltaT.deltaT)
    val onLocalDeltaChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.deltaT = arg.asInstanceOf[Double]}
    gui.add(obj, "DeltaT").min(0.0009).max(2.2).step(0.00001).onChange(onLocalDeltaChange)

    var liveSpeed = 0.005;
    var lifeTime = 0.5
    var size = 50

    var pointCloud = new js.Array[Particle]
    val loader = new TextureLoader()
    val texture: Texture = loader.load("assets/images/textures/particle.png")


    var shaderMaterial = new ShaderMaterial(js.Dynamic.literal(
      vertexShader = $("#vertex-shader").text(),
      fragmentShader = $("#fragment-shader").text()
    ))


    def addRandomParticle(n: Int = 1): Unit = {

      for(_ <- 0 to n) {

        var material = new PointsMaterial(js.Dynamic.literal(map = texture, depthTest = 0, side = BackSide, transparent = true, blending = AdditiveBlending));
        var geometry = new Geometry();

        val x = (Math.random() * 30) - 15;
        val y = (Math.random() * 30) - 15;
        val z = (Math.random() * 30) - 15;
        geometry.vertices.push(new Vector3(x, y, z));

        var particle = new Particle(geometry, shaderMaterial, 1, 2, lifeTime);
        particle.setPosition(mousePos)
        particle.mesh.material.size = size
        particle.applyForce(Force.customForce((Math.random() * 300) - 150, (Math.random() * 1000), (Math.random() * 300) - 150))
        pointCloud.push(particle)
        scene.add(particle.mesh);
      }

    }


    def renderLoop(timestamp: Double): Unit = {
      dom.window.requestAnimationFrame(renderLoop _)
      controls.update()
      renderer.render(scene, camera)

      addRandomParticle(5)


      for((particle, index) <- pointCloud.zipWithIndex){

        particle.mesh.material.size = (lifeTime-particle.lived)/lifeTime*(size/2)
        particle.applyForce(Force.earthGravitationForce(particle))
        particle.recalculateEulerPosition()
        particle.lived+=liveSpeed
        if(particle.lived>=particle.lifeTime) {scene.remove(particle.mesh)}
      }

      pointCloud = pointCloud.filter(particle => particle.lived <= particle.lifeTime)
    }



    renderLoop(System.currentTimeMillis())

    dom.window.addEventListener( "resize", (onWindowResize _), false)
    dom.window.addEventListener("mousemove", (onMouseMove _), false);


    def onWindowResize[T](arg: T) = {
      camera.aspect = dom.window.innerWidth / dom.window.innerHeight
      camera.updateProjectionMatrix()
      renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)
    }


  def onMouseMove(event: dom.MouseEvent) {
    event.preventDefault();



    var vec = new Vector3(
      ( event.clientX / dom.window.innerWidth ) * 2 - 1,
      - ( event.clientY / dom.window.innerHeight ) * 2 + 1,
      0.5 );

    vec = vec.unproject( camera );

    vec = vec.sub( camera.position ).normalize();
    var distance = - camera.position.z / vec.z;
    mousePos.copy( camera.position ).add( vec.multiplyScalar( distance ) );


  }


  }

}
