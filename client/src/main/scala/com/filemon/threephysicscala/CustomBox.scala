package com.filemon.threephysicscala

import physics.webapp.Physics.{Constants, DeltaT}
import physics.webapp.THREE.Geometryimport.Geometry._
import physics.webapp.THREE.Materials.{DoubleSide, MeshNormalMaterial, MeshPhongMaterial}
import physics.webapp.THREE.Math.{Vector2, Vector3}
import physics.webapp.THREE.THREE.Face3

import scala.scalajs.js

case class PhysicVertex(var pos: Vector3, var vertexMass: Double) extends Constants  {

  var prevPosition: Vector3 = new Vector3(pos.x, pos.y, pos.z)
  var force: Vector3 = new Vector3(0, 0, 0)

  var springDistnace: Double = 1;
  var initialDistances: Vector3 = new Vector3(0,0,0)

  def clearForces() = {
    force = new Vector3(0, 0, 0)
  }

  def addGravitationForce() = {
    force.y += - vertexMass * EARTH_G

    //if(pos.y > 2 && pos.y < 8.5) force.z += DeltaT.windForce
  }

  def addForce(f: Vector3) = {
    force.x -= f.x
    force.y -= f.y
    force.z -= f.z
  }

  def initFirstPosition() = {
    prevPosition.x = pos.x + DeltaT.deltaT * DeltaT.deltaT * force.x / vertexMass
    prevPosition.y = pos.y + DeltaT.deltaT * DeltaT.deltaT * force.y / vertexMass
    prevPosition.z = pos.z + DeltaT.deltaT * DeltaT.deltaT * force.z / vertexMass
  }

  def calculateEulerPosition() = {
    pos.x = pos.x + DeltaT.deltaT * DeltaT.deltaT * force.x / vertexMass
    pos.y = pos.y + DeltaT.deltaT * DeltaT.deltaT * force.y / vertexMass
    pos.z = pos.z + DeltaT.deltaT * DeltaT.deltaT * force.z / vertexMass
  }

  def calculateVerletPosition() = {
    val verletVector = generateVerletVector()
    prevPosition = pos.clone()

    pos = verletVector
  }

  def generateVerletVector() = {

    val verlet_x : Double = (2 * pos.x) - prevPosition.x + (DeltaT.deltaT * DeltaT.deltaT) * (force.x / vertexMass)
    val verlet_y : Double = (2 * pos.y) - prevPosition.y + (DeltaT.deltaT * DeltaT.deltaT) * (force.y / vertexMass)
    val verlet_z : Double = (2 * pos.z) - prevPosition.z + (DeltaT.deltaT * DeltaT.deltaT) * (force.z / vertexMass)

    new Vector3(verlet_x, verlet_y, verlet_z)
  }

  def bottomCollisionCheck(initSpherePosition: Double, boxHeight: Double) = {
    val substractor = boxHeight/2
    if( pos.y < 0 ) pos.y = prevPosition.y
    //if( (pos.y + initSpherePosition) < 0 ) pos.y = prevPosition.y
   // else if( (pos.y + initSpherePosition) < 0.1 && pos.x <= 0 ) pos.y = prevPosition.y

  }

}

case class SpringConnection(index1: Int, index2: Int, springForce: Int, _firstPos1: Vector3, _firstPos2: Vector3) extends Operands {

  val restLength = distanceVector3(_firstPos1, _firstPos2)
  val springConstants = springForce

}

trait CustomBox {


  class SpringGeometry(wSize: Int, hSize: Int, wSeg: Int, hSeg: Int, vertexMass: Double) extends Constants with Operands {

    val geom = new PlaneGeometry(wSize, hSize, wSeg, hSeg)
    val material = new MeshPhongMaterial( js.Dynamic.literal(color = 0x800000, emissive = 0x330000, wireframe= true, side = DoubleSide, flatShading = true) );

    val initialSpherePosition = 10

    val sphere = new Mesh( geom, material );
    sphere.position.y = initialSpherePosition;
    sphere.updateMatrixWorld(true)
    sphere.rotation.z = Math.PI / 3;
    sphere.updateMatrixWorld(true)


    val dampingCoefficient = 10
    val distanceTolerance = 0.2

    var physicVertex = new js.Array[PhysicVertex]()
    var springConnections = new js.Array[SpringConnection]()

    for ((v, index) <- sphere.geometry.vertices.zipWithIndex){
      val modifyPosition = sphere.localToWorld(v)
        physicVertex.push( new PhysicVertex(modifyPosition, vertexMass) )
    }


    def initFirstPositionStep() = {
      for (v <- physicVertex) {
        //println(v.pos.y)
        v.clearForces()
        v.addGravitationForce()
        v.initFirstPosition()
      }
    }

    def letsGoOverEveryVertex() = {
      //println(springConnections(0).restLength)
      //println(physicVertex(0).pos.y)

      addSpingForces()

      for ((v, index) <- physicVertex.zipWithIndex) {
        val pV: PhysicVertex = v.copy()
        v.addGravitationForce()
        v.calculateVerletPosition()
        applyNewVerticesPosition(pV, index)
        v.bottomCollisionCheck(initialSpherePosition, hSize)
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

      for(index <- 0 to (maxIndex) ){

        if(index<=maxIndex-step){
          pushString(index, index+step)
        }

        //else pushString(index-step, index)
      }
    }

    //AUTO
    def addSpringOther() = {
      val forceBorders = 1000
      val forceCorners = 1000

      val maxIndex = physicVertex.length-1

      val corner0 = 0
      val corner1 = wSeg
      val corner2 = maxIndex-wSeg
      val corner3 = maxIndex

      //Middle
      if((wSeg+1)%2 == 1) {
        pushString((wSeg/2).toInt, maxIndex-((wSeg/2).toInt), forceBorders)
      } else if((wSeg+1)%2 == 0) {
        pushString((wSeg/2)-1, maxIndex-((wSeg/2)+1), forceBorders)
        pushString((wSeg/2)+1, maxIndex-((wSeg/2)-1), forceBorders)
      }


      //Borders
      pushString(corner0, corner2, forceBorders)
      pushString(corner0, corner1, forceBorders)
      pushString(corner1, corner3, forceBorders)
      pushString(corner2, corner3, forceBorders)

      //Corners
      pushString(corner0, corner3, forceCorners)
      pushString(corner1, corner2, forceCorners)
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



    def pushString(index1: Int, index2: Int, springForce: Int = 100) = {
      springConnections.push(SpringConnection(index1, index2, springForce, physicVertex(index1).pos, physicVertex(index2).pos))
    }

    def addSpringConnections() = {
      pushString(0, 6)
      pushString(1, 7)
      pushString(2, 8)
      pushString(3, 9)
      pushString(4, 10)
      pushString(5, 11)

      pushString(6, 12)
      pushString(7, 13)
      pushString(8, 14)
      pushString(9, 15)
      pushString(10, 16)
      pushString(11, 17)

      pushString(12, 18)
      pushString(13, 19)
      pushString(14, 20)
      pushString(15, 21)
      pushString(16, 22)
      pushString(17, 23)

      pushString(18, 24)
      pushString(19, 25)
      pushString(20, 26)
      pushString(21, 27)
      pushString(22, 28)
      pushString(23, 29)

      pushString(24, 30)
      pushString(25, 31)
      pushString(26, 32)
      pushString(27, 33)
      pushString(28, 34)
      pushString(29, 35)
    }




   /*





    def addSpringForceGlobal(v: PhysicVertex, index: Int) = {
      val force = calculateSpringForceVector(index)
      v.force.x += force.x
      v.force.y += force.y
      v.force.z += force.z
    }

    def sumVectors(v1: Vector3, v2: Vector3): Vector3 = {
      new Vector3(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z)
    }


    def calculateSpringForceVector(index: Int): Vector3 = {
      val connectedTo = physicVertex(index).connectedToIndex
      val staticDistance = physicVertex(index).initialDistances.clone()

      val yDisPrev = Math.sqrt(Math.pow((physicVertex(connectedTo).prevPosition.clone().y - physicVertex(index).prevPosition.clone().y), 2))
      val yDis = Math.sqrt(Math.pow((physicVertex(connectedTo).pos.clone().y - physicVertex(index).pos.clone().y), 2))



      if(yDis!=0) {
        val yVelocity = yDis - yDisPrev
        val f = (yDis - staticDistance.y) * springForce + (yVelocity * (physicVertex(index).pos.clone().y - physicVertex(connectedTo).pos.clone().y)) * dampingCoefficient / yDis;
        val efIgrek = ((physicVertex(index).pos.clone().y - physicVertex(connectedTo).pos.clone().y) / yDis) * f
        //val calcForceY = -1 * springForce * (yDis - staticDistance.y) * (physicVertex(index).pos.clone().y / yDis) - dampingCoefficient * yVelocity
        //println(efIgrek)
        new Vector3(0, efIgrek, 0)
      } else new Vector3(0, 0, 0)


    }

    def distanceVector(index: Int, connectedTo:Int): Vector3 = {
      val xDis = Math.sqrt(Math.pow((physicVertex(connectedTo).prevPosition.clone().x - physicVertex(index).prevPosition.clone().x), 2))
      val yDis = Math.sqrt(Math.pow((physicVertex(connectedTo).prevPosition.clone().y - physicVertex(index).prevPosition.clone().y), 2))
      val zDis = Math.sqrt(Math.pow((physicVertex(connectedTo).prevPosition.clone().z - physicVertex(index).prevPosition.clone().z), 2))
      new Vector3(xDis, yDis, zDis)

    }

    def initDistances() = {
      for ((v, index) <- physicVertex.zipWithIndex) {
        v.initialDistances = distanceVector(index, v.connectedToIndex).clone()
      }
    }



    def colisionCheck(v: PhysicVertex, index: Int) = {

      val connectedTo = physicVertex(index).connectedToIndex
      val staticDistance = physicVertex(index).initialDistances.clone()

      val yDis = Math.sqrt( Math.pow( (physicVertex(index).pos.clone().y - physicVertex(connectedTo).pos.clone().y), 2)  )
      var pos = sphere.localToWorld(physicVertex(index).pos.clone())


      if(yDis<=(staticDistance.y*distanceTolerance) ) {
       // println(physicVertex(index).initialDistances.y+" : "+staticDistance.y*distanceTolerance)
        physicVertex(index).pos.y = physicVertex(index).prevPosition.clone().y
      }
      else if (pos.y <= 0) {
        pos.y = 0
        pos = sphere.worldToLocal(pos.clone())
        physicVertex(index).pos.y = pos.y
      }

     // physicVertex(index).pos = pos.clone()

    }



    def calculateVerletPosition(v: PhysicVertex, index: Int) = {

      val currentPosition = physicVertex(index).pos.clone()
      val prevPosition = physicVertex(index).prevPosition.clone()
      val force = physicVertex(index).force.clone()

      //2*r(n) - r(n-1) + h*h* f(n) / m
      val verlet_x : Double = (2 * currentPosition.x) - prevPosition.x + (DeltaT.deltaT * DeltaT.deltaT) * (force.x / v.vertexMass)
      val verlet_y : Double = (2 * currentPosition.y) - prevPosition.y + (DeltaT.deltaT * DeltaT.deltaT) * (force.y / v.vertexMass)
      val verlet_z : Double = (2 * currentPosition.z) - prevPosition.z + (DeltaT.deltaT * DeltaT.deltaT) * (force.z / v.vertexMass)

      physicVertex(index).prevPosition = currentPosition.clone()

      physicVertex(index).pos.x = verlet_x
      physicVertex(index).pos.y = verlet_y
      physicVertex(index).pos.z = verlet_z
    }
*/
  }



}
