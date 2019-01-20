package physics.webapp


import com.filemon.threephysicscala.CustomBox
import com.filemon.threephysicscala.datgui.GUI

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import org.scalajs.dom
import dom.document
import physics.webapp.Environment.{Background, Planets}
import physics.webapp.Physics._
import physics.webapp.Physics.Force
import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Camera.{OrbitControls, PerspectiveCamera, TrackballControls}
import physics.webapp.THREE.Geometryimport.Geometry._
import physics.webapp.THREE.Lights.{AmbientLight, PointLight}
import physics.webapp.THREE.Materials._
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.Objects.{Line, Scene, WebGLRenderer}

import scala.scalajs.js

@JSExportTopLevel("physics.webapp.ScalaProject")
@JSExportAll
object ScalaProject extends Constants with Planets with Background with CustomBox {

  def createPlane(): Mesh = {
    val geometry = new PlaneGeometry( 30, 30, 32 );
    val material = new MeshPhongMaterial( js.Dynamic.literal(color = 0x004210, side = DoubleSide, flatShading = true) );
    val mesh = new Mesh( geometry, material );
    mesh.rotation.x = Math.PI / 2;
    mesh.position.y = -0.1
    mesh
  }

  def addLight(x: Int, y: Int, z: Int): PointLight = {
    val light = new PointLight( 0xffffff, 1, 0 );
    light.position.set( x, y, z );
    light
  }

  def createTestCube(): Unit = {

    val scene = new Scene()
    val camera = new PerspectiveCamera(75, dom.window.innerWidth / dom.window.innerHeight, 0.1, 90000)
    val renderer = new WebGLRenderer(js.Dynamic.literal(antialias=true))
    renderer.setPixelRatio( dom.window.devicePixelRatio );
    renderer.setSize( dom.window.innerWidth, dom.window.innerHeight );
    renderer.setClearColor( 0x000000, 1 );

    dom.document.body.appendChild(renderer.domElement)
    renderer.setSize(dom.window.innerWidth, dom.window.innerHeight)

    val vertexMass = 1

    val springWSize = 5
    val springHSize = 8

    val springGeometry = new SpringGeometry(springWSize, springHSize, 20, 20, vertexMass)

    val controls = new TrackballControls(camera, renderer.domElement)
    val gui = new GUI(js.Dynamic.literal(resizable= false, width=300))

    var obj = js.Dynamic.literal(DeltaT=DeltaT.deltaT, WindForce=DeltaT.windForce, VertexMax=vertexMass, Gravity=DeltaT.gravity)

    val onLocalDeltaChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.deltaT = arg.asInstanceOf[Double]}
    val onWindForceChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.windForce = arg.asInstanceOf[Double]}
    val onVertexMassChange: js.Any => js.Any = (arg: js.Any) => {
      for((v, index) <- springGeometry.physicVertex.zipWithIndex){
         v.vertexMass = arg.asInstanceOf[Double]
        }
      }

    val onGravityChange: js.Any => js.Any = (arg: js.Any) =>{DeltaT.gravity = arg.asInstanceOf[Double]}

    controls.rotateSpeed = 2.0
    controls.zoomSpeed = 0.6
    controls.minDistance = 1
    controls.maxDistance = 50
    controls.dynamicDampingFactor = 0.38

    //gui.add(obj, "DeltaT").min(0.010).max(1.1).step(0.00001).onChange(onLocalDeltaChange)
    gui.add(obj, "WindForce").min(0.001).max(10.0).step(0.001).onChange(onWindForceChange)
    //gui.add(obj, "VertexMax").min(1).max(3.0).step(0.01).onChange(onVertexMassChange)
    gui.add(obj, "Gravity").min(1).max(10).step(1.0).onChange(onGravityChange)


    camera.position.z = 25
    camera.position.y = 5
    //val starBackground = generateBackground(1000,  "assets/images/textures/galaxy_starfield7.jpg" )
    //scene.add( addLight(0, 1000, 2000) )
    //scene.add( addLight(5, 20, 5) )
    //scene.add( addLight(5,-20,- 5) )
    //scene.add( addLight(-100, -200, -10) )
    scene.add( new AmbientLight(0xffffff, 2) )


    val sun = createPlanet(200, sunMass, "assets/images/textures/texture_mars.jpg")
    sun.mesh.position.y = -200
    sun.mesh.position.x = -springHSize/2
    sun.mesh.rotation.z = Math.PI / 1.3;

    scene.add( sun.mesh );

    var cylGeo = new CylinderGeometry( 0.1, 0.1, 12.5, 32 );
    var cylMat = new MeshBasicMaterial( js.Dynamic.literal(color= 0x701900) );
    var cylinder = new Mesh( cylGeo, cylMat );
    cylinder.position.x = -springHSize/2
    cylinder.position.y = 6.25
    scene.add( cylinder );

    val starBackground = generateBackground(1000,  "assets/images/textures/galaxy_starfield7.jpg" )
    scene.add(starBackground)

    springGeometry.addSpringAlgConnections()
    springGeometry.addSpringConnectionsMixed()
    springGeometry.addSpringHorizontal()
    springGeometry.addSpringOther()

    scene.add( springGeometry.sphere );

    springGeometry.initFirstPositionStep()
    springGeometry.sphere.updateMatrixWorld(true)
    springGeometry.sphere.geometry.verticesNeedUpdate = true;
    println(springGeometry.sphere.geometry.vertices.length)

    val material = new LineBasicMaterial(js.Dynamic.literal(color = 0x909090))

    def renderLoop(timestamp: Double): Unit = {
      dom.window.requestAnimationFrame(renderLoop _)
      controls.update()
      springGeometry.letsGoOverEveryVertex()
      renderer.render(scene, camera)
      springGeometry.sphere.updateMatrixWorld(true)
      springGeometry.sphere.geometry.verticesNeedUpdate = true;

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


