package com.filemon.threephysicscala

import physics.webapp.Physics.{Constants, DeltaT}
import physics.webapp.THREE.Geometryimport.Geometry._
import physics.webapp.THREE.Materials._
import physics.webapp.THREE.Math.{Vector2, Vector3}
import physics.webapp.THREE.THREE.Face3

import scala.scalajs.js

case class SpringConnection(index1: Int, index2: Int, springForce: Int, _firstPos1: Vector3, _firstPos2: Vector3) extends Operands {

  val restLength = distanceVector3(_firstPos1, _firstPos2)
  val springConstants = springForce
  val minDistance = 0.1 * restLength

}

trait CustomBox {

  class SpringGeometry(wSize: Int, hSize: Int, wSeg: Int, hSeg: Int, vertexMass: Double) extends Constants with Operands {

    val geom = new PlaneGeometry(wSize, hSize, wSeg, hSeg)
    val textureLoader = new TextureLoader()
    val texture: Texture = textureLoader.load("assets/images/textures/isspFlaga.png")
   // val material = new MeshBasicMaterial(js.Dynamic.literal(map=texture, depthTest=0, side=BackSide, transparent=true, blending=AdditiveBlending ))


    val material = new MeshPhongMaterial( js.Dynamic.literal(map=texture, wireframe= true, side = DoubleSide, flatShading = true) );

    val initialSpherePosition = 10

    val sphere = new Mesh( geom, material );
    sphere.position.y = initialSpherePosition;
    sphere.updateMatrixWorld(true)
    sphere.rotation.z = Math.PI / 2;
    sphere.updateMatrixWorld(true)

    val dampingCoefficient = 10
    val distanceTolerance = 0.2

    var physicVertex = new js.Array[PhysicVertex]()
    var springConnections = new js.Array[SpringConnection]()
    var attachedIndexes = new js.Array[Int]()

    for(i <- 0 to 20){
      attachedIndexes.push(i)
    }



    for ((v, index) <- sphere.geometry.vertices.zipWithIndex){
      val modifyPosition = sphere.localToWorld(v)
        physicVertex.push( new PhysicVertex(modifyPosition, vertexMass) )
    }


    def initFirstPositionStep() = {
      for (v <- physicVertex) {
        v.clearForces()
        v.addGravitationForce()
        v.initFirstPosition()
      }
    }

    def letsGoOverEveryVertex() = {
      addSpingForces()
      for ((v, index) <- physicVertex.zipWithIndex) {
        val pV: PhysicVertex = v.copy()
        val attached = attachedIndexes.contains(index)

        v.addGravitationForce()
        v.calculateVerletPosition(attached)
        v.bottomCollisionCheck()
        applyNewVerticesPosition(pV, index)
        v.clearForces()
      }

    }

    def springCollisionCheck(spr: SpringConnection) = {



    }

    def addSpingForces() = {
       for(spr <- springConnections) {
         val vertex1 = physicVertex(spr.index1).copy()
         val vertex2 = physicVertex(spr.index2).copy()

         val vx_1 = distanceCalc(vertex1.pos.x, vertex1.prevPosition.x) / DeltaT.deltaT
         val vy_1 = distanceCalc(vertex1.pos.y, vertex1.prevPosition.y) / DeltaT.deltaT
         val vz_1 = distanceCalc(vertex1.pos.z, vertex1.prevPosition.z) / DeltaT.deltaT

         val vx_2 = distanceCalc(vertex2.pos.x, vertex2.prevPosition.x) / DeltaT.deltaT
         val vy_2 = distanceCalc(vertex2.pos.y, vertex2.prevPosition.y) / DeltaT.deltaT
         val vz_2 = distanceCalc(vertex2.pos.z, vertex2.prevPosition.z) / DeltaT.deltaT

         val vx_12 = vx_1 - vx_2
         val vy_12 = vy_1 - vy_2
         val vz_12 = vz_1 - vz_2

         val prevDistance = distanceVector3(vertex1.prevPosition, vertex2.prevPosition)
         val currDistance = distanceVector3(vertex1.pos, vertex2.pos)


         if(currDistance!=0) {
           val yVelocity = (currDistance - prevDistance) / DeltaT.deltaT
           val force = (currDistance - spr.restLength) * spr.springConstants + (vx_12 * (vertex1.pos.x - vertex2.pos.x) + vy_12 * (vertex1.pos.y - vertex2.pos.y) + vz_12 * (vertex1.pos.z - vertex2.pos.z)) * dampingCoefficient / currDistance

           val fx = ((vertex1.pos.x - vertex2.pos.x) / currDistance) * force
           val fy = ((vertex1.pos.y - vertex2.pos.y) / currDistance) * force
           val fz = ((vertex1.pos.z - vertex2.pos.z) / currDistance) * force

           physicVertex(spr.index1).addForce(new Vector3(fx, fy, fz))
           physicVertex(spr.index2).addForce(new Vector3(-fx, -fy, -fz))
         }



       }
    }



    def applyNewVerticesPosition(v: PhysicVertex, index: Int) = {
      val newPosition = sphere.worldToLocal(v.pos.clone())
      sphere.geometry.vertices(index).copy(newPosition)
    }


    def addSpringAlgConnections() = {
      val maxIndex = physicVertex.length-1
      val step = wSeg+1
      val springForce = 1000

      for(index <- 0 to (maxIndex) ){

        if(index<=maxIndex-step){
          pushString(index, index+step, springForce)
        }

        //else pushString(index-step, index)
      }
    }

    //AUTO
    def addSpringOther() = {
      val forceBorders = 1000
      val forceCorners = 1000

      val maxIndex = physicVertex.length-1
      val step = wSeg+1

      val corner0 = 0
      val corner1 = wSeg
      val corner2 = maxIndex-wSeg
      val corner3 = maxIndex


      for(index <- 0 to wSeg){
        pushString(index, (maxIndex-wSeg)+index, forceBorders)
        pushString(index*step, (index*step)+wSeg, forceBorders)

      }


     /* //Middle
      if((wSeg+1)%2 == 1) {
        pushString((wSeg/2).toInt, maxIndex-((wSeg/2).toInt), forceBorders)
      } else if((wSeg+1)%2 == 0) {
        pushString((wSeg/2)-1, maxIndex-((wSeg/2)+1), forceBorders)
        pushString((wSeg/2)+1, maxIndex-((wSeg/2)-1), forceBorders)
      }*/


      //Borders
      //pushString(corner0, corner2, forceBorders)
      //pushString(corner0, corner1, forceBorders)
      //pushString(corner1, corner3, forceBorders)
      //pushString(corner2, corner3, forceBorders)

      //Corners
      //pushString(corner0, corner3, forceCorners)
      //pushString(corner1, corner2, forceCorners)
    }

    def addSpringHorizontal() = {
      val maxIndex = physicVertex.length-1
      val step = wSeg+1

      val forceHorizontal = 1000

      for(index <- 0 to (maxIndex) ){
        if((index+1) % step != 0 )  pushString(index, index+1, forceHorizontal)
      }

    }

    def addSpringConnectionsMixed() = {
      val maxIndex = physicVertex.length-1
      val stepShort = wSeg+1
      val stepLong = wSeg+2

      val forceMixed = 100

      for(index <- 0 to (maxIndex) ){
        if((index+1) % stepShort != 0 && index <= maxIndex-stepShort)  {

          pushString(index, index+stepLong, forceMixed)
          pushString((index+1), index+stepShort, forceMixed)

        }
      }

    }


    def pushString(index1: Int, index2: Int, springForce: Int) = {
      springConnections.push(SpringConnection(index1, index2, springForce, physicVertex(index1).pos, physicVertex(index2).pos))
    }

  }



}
