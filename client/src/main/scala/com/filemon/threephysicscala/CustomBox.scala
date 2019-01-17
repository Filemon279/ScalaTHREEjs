package com.filemon.threephysicscala

import physics.webapp.Physics.{Constants, DeltaT}
import physics.webapp.THREE.Geometryimport.Geometry._
import physics.webapp.THREE.Materials.{DoubleSide, MeshNormalMaterial, MeshPhongMaterial}
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.THREE.Face3

import scala.scalajs.js

case class SpringConnection(index1: Int, index2: Int)

trait CustomBox {


  class SpringGeometry(wSize: Int, hSize: Int, wSeg: Int, hSeg: Int, vertexMass: Double) extends Constants {

    val geom = new PlaneGeometry(wSize, hSize, wSeg, hSeg)

    val material = new MeshPhongMaterial( js.Dynamic.literal(color = 0x800000, emissive = 0x330000, wireframe= true, side = DoubleSide, flatShading = true) );

    val sphere = new Mesh( geom, material );

    var springForce = 50
    val dampingCoefficient = 20
    val distanceTolerance = 0.1

    val physicVertex = new js.Array[PhysicVertex](geom.vertices.length)
    val springConnections = new js.Array[SpringConnection]()

    for ((v, index) <- sphere.geometry.clone().vertices.zipWithIndex){
      val newRowIndex = (wSeg+1)
      val lastRow = newRowIndex*(hSeg)

      if(index < newRowIndex) {
        physicVertex(index) = new PhysicVertex(v.clone(), vertexMass, index+newRowIndex)
      }

      else if(index >= newRowIndex && index < lastRow) {
        physicVertex(index) = new PhysicVertex(v.clone(), vertexMass, index+newRowIndex)
      }

      else if(index >= lastRow) {
        physicVertex(index) = new PhysicVertex(v.clone(), vertexMass, index-newRowIndex)
      }

    }

    def initFirstPosition() = {
      for ((_, index) <- physicVertex.zipWithIndex) {
        val force = physicVertex(index).force.clone()

        val position = physicVertex(index).position.clone()


        val x = position.x + DeltaT.deltaT * DeltaT.deltaT * force.x / vertexMass
        val y = position.y + DeltaT.deltaT * DeltaT.deltaT * force.y / vertexMass
        val z = position.z + DeltaT.deltaT * DeltaT.deltaT * force.z / vertexMass
        physicVertex(index).prevPosition.x = x
        physicVertex(index).prevPosition.y = y
        physicVertex(index).prevPosition.z = z
      }
    }

    def letsGoOverEveryVertex() = {

      for ((v, index) <- physicVertex.zipWithIndex) {
        clearForces(v, index)
        addGravitationForce(v, index)
        addSpringForceGlobal(v, index)
        calculateVerletPosition(v, index)
        colisionCheck(v, index)
        applyNewVerticesPosition(v, index)
      }

    }


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
      val yDis = Math.sqrt(Math.pow((physicVertex(connectedTo).position.clone().y - physicVertex(index).position.clone().y), 2))
      if(yDis!=0) {
        val yVelocity = yDis - yDisPrev
        val f = (yDis - staticDistance.y) * springForce + (yVelocity * (physicVertex(index).position.clone().y - physicVertex(connectedTo).position.clone().y)) * dampingCoefficient / yDis;
        val efIgrek = ((physicVertex(index).position.clone().y - physicVertex(connectedTo).position.clone().y) / yDis) * f
         val calcForceY = -1 * springForce * (yDis - staticDistance.y) * (physicVertex(index).position.clone().y / yDis) - dampingCoefficient * yVelocity
        println(efIgrek)
        new Vector3(0, calcForceY, 0)
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

    def applyNewVerticesPosition(v: PhysicVertex, index: Int) = {
      sphere.geometry.vertices.apply(index).copy(v.position.clone())
    }

    def colisionCheck(v: PhysicVertex, index: Int) = {

      val connectedTo = physicVertex(index).connectedToIndex
      val staticDistance = physicVertex(index).initialDistances.clone()

      val yDis = Math.sqrt( Math.pow( (physicVertex(index).position.clone().y - physicVertex(connectedTo).position.clone().y), 2)  )
      var position = sphere.localToWorld(physicVertex(index).position.clone())


      if(yDis<=(staticDistance.y*distanceTolerance) ) {
       // println(physicVertex(index).initialDistances.y+" : "+staticDistance.y*distanceTolerance)
        physicVertex(index).position.y = physicVertex(index).prevPosition.clone().y
      }
      else if (position.y <= 0) {
        position.y = 0
        position = sphere.worldToLocal(position.clone())
        physicVertex(index).position.y = position.y
      }

     // physicVertex(index).position = position.clone()

    }

    def clearForces(v: PhysicVertex, index: Int) = {
      v.force = new Vector3(0, 0, 0)
    }

    def addGravitationForce(v: PhysicVertex, index: Int) = {
      v.force.y = - v.vertexMass * EARTH_G
    }

    def calculateVerletPosition(v: PhysicVertex, index: Int) = {

      val currentPosition = physicVertex(index).position.clone()
      val prevPosition = physicVertex(index).prevPosition.clone()
      val force = physicVertex(index).force.clone()

      //2*r(n) - r(n-1) + h*h* f(n) / m
      val verlet_x : Double = (2 * currentPosition.x) - prevPosition.x + (DeltaT.deltaT * DeltaT.deltaT) * (force.x / vertexMass)
      val verlet_y : Double = (2 * currentPosition.y) - prevPosition.y + (DeltaT.deltaT * DeltaT.deltaT) * (force.y / vertexMass)
      val verlet_z : Double = (2 * currentPosition.z) - prevPosition.z + (DeltaT.deltaT * DeltaT.deltaT) * (force.z / vertexMass)

      physicVertex(index).prevPosition = currentPosition.clone()

      physicVertex(index).position.x = verlet_x
      physicVertex(index).position.y = verlet_y
      physicVertex(index).position.z = verlet_z
    }

  }



}
