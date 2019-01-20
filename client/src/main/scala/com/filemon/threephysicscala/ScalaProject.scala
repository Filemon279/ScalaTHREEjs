package physics.webapp


import com.filemon.threephysicscala.{CustomBox}
import com.filemon.threephysicscala.datgui.GUI

import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}
import org.scalajs.dom
import dom.document
import physics.webapp.Environment.{Background, Planets}
import physics.webapp.Physics._
import physics.webapp.Physics.Force
import physics.webapp.SpaceObjects.Planet
import physics.webapp.THREE.Camera.{OrbitControls, PerspectiveCamera, TrackballControls}
import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh, PlaneGeometry, SphereGeometry}
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
    val material = new MeshPhongMaterial( js.Dynamic.literal(color = 0x156289, emissive = 0x072534, side = DoubleSide, flatShading = true) );
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

    val vertexMass = 0.9

    val springGeometry = new SpringGeometry(10, 10, 20, 20, vertexMass)

    val controls = new TrackballControls(camera, renderer.domElement)
    val gui = new GUI(js.Dynamic.literal(resizable= false, width=300))
    var obj = js.Dynamic.literal(DeltaT=DeltaT.deltaT, WindForce=DeltaT.windForce, VertexMax=vertexMass, SpringForce=10000)
    val onLocalDeltaChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.deltaT = arg.asInstanceOf[Double]}
    val onWindForceChange: js.Any => js.Any = (arg: js.Any) => {DeltaT.windForce = arg.asInstanceOf[Double]}
    val onVertexMassChange: js.Any => js.Any = (arg: js.Any) => {
      for((v, index) <- springGeometry.physicVertex.zipWithIndex){
         v.vertexMass = arg.asInstanceOf[Double]
        }
      }

    val onSpringForceChange: js.Any => js.Any = (arg: js.Any) => {
      //springGeometry.springConstants = arg.asInstanceOf[Int]
    }

    controls.rotateSpeed = 2.0
    controls.zoomSpeed = 0.6
    controls.minDistance = 1
    controls.dynamicDampingFactor = 0.38


    //gui.add(obj, "DeltaT").min(0.010).max(1.1).step(0.00001).onChange(onLocalDeltaChange)
    //gui.add(obj, "WindForce").min(0.001).max(1.0).step(0.001).onChange(onWindForceChange)
    //gui.add(obj, "VertexMax").min(0.1).max(100.0).step(0.01).onChange(onVertexMassChange)
    //gui.add(obj, "SpringForce").min(1).max(10000).step(1.0).onChange(onSpringForceChange)


    camera.position.z = 25
    camera.position.y = 5
    //val starBackground = generateBackground(1000,  "assets/images/textures/galaxy_starfield7.jpg" )
    scene.add( addLight(0, 1000, 2000) )
    scene.add( addLight(5, 20, 5) )
    scene.add( addLight(-100, -200, -10) )
    val plane = createPlane()
    scene.add( plane );

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


 /*   for((v, index) <- springGeometry.physicVertex.zipWithIndex){
      if(v.connectedToIndex > 0) {
        val testSphere = new SphereGeometry(0.1, 5, 5)
        val material = new MeshPhongMaterial(js.Dynamic.literal(color = 0x909090, side = DoubleSide, flatShading = true));
        val sphere = new Mesh(testSphere, material);
        val spherePosition = springGeometry.sphere.localToWorld(v.prevPosition.clone())
        val linkedToPosition = springGeometry.sphere.localToWorld(springGeometry.physicVertex(v.connectedToIndex).prevPosition.clone())
        sphere.position.set(spherePosition.x, spherePosition.y, spherePosition.z)
        scene.add(sphere);

        var geometry = new Geometry();
        geometry.vertices.push(
          new Vector3(spherePosition.x, spherePosition.y, spherePosition.z),
          new Vector3(linkedToPosition.x, linkedToPosition.y, linkedToPosition.z),
        );

        println("index: "+index+" : "+v.connectedToIndex)
        println(v.initialDistances.x+", "+v.initialDistances.y+", "+v.initialDistances.z+", ")

        var line = new Line(geometry, material);
        scene.add(line);
      }

    }*/

  //println(springGeometry.sphere.geometry.vertices(10).x=6)
 // println(springSphereGeometry.sphere.geometry.vertices(31).y)

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


