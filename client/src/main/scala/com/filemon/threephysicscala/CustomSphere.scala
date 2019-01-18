
package com.filemon.threephysicscala
/*
import physics.webapp.Physics.{Constants, DeltaT}
import physics.webapp.THREE.Geometryimport.Geometry.{Geometry, Mesh, SphereGeometry}
import physics.webapp.THREE.Materials.{DoubleSide, MeshNormalMaterial, MeshPhongMaterial}
import physics.webapp.THREE.Math.Vector3
import physics.webapp.THREE.THREE.Face3

import scala.scalajs.js




trait CustomSphere {


  class SpringGeometry(radius: Int, wSeg: Int, hSeg: Int, vertexMass: Double) extends Constants {

    val geom = new SphereGeometry(radius, wSeg, hSeg)

    val material = new MeshPhongMaterial( js.Dynamic.literal(color = 0x800000, emissive = 0x330000, wireframe= true, side = DoubleSide, flatShading = true) );

    val sphere = new Mesh( geom, material );

    val springForce = 5
    val dampingCoefficient = 2
    val distanceTolerance = 0.8

    val physicVertex = new js.Array[PhysicVertex](geom.vertices.length)

    for ((v, index) <- sphere.geometry.clone().vertices.zipWithIndex){

      val newRowIndex = (wSeg+1)
      val lastRow = (wSeg-1)*(hSeg)

      if(index == 0) {
        val connectedTo = 1
        physicVertex(index) = new PhysicVertex(v, vertexMass)
      }

      if(index > 0 && index < newRowIndex) {
        val connectedTo = index+wSeg
        physicVertex(index) = new PhysicVertex(v, vertexMass, connectedTo)

      }

      else if(index >= newRowIndex && index <= lastRow-wSeg) {
        val connectedTo = index-wSeg
        physicVertex(index) = new PhysicVertex(v, vertexMass, connectedTo)

      }

      else if(index >= newRowIndex && index >= lastRow-wSeg) {
        val connectedTo = index-wSeg
        physicVertex(index) = new PhysicVertex(v, vertexMass, connectedTo)

      }

      if(index == lastRow+1) {
        val connectedTo = lastRow
        physicVertex(index) = new PhysicVertex(v, vertexMass, connectedTo)

      }

    /*  else if(index >= lastRow) {
        physicVertex(index) = new PhysicVertex(v, vertexMass, index-newRowIndex)
      }*/

    }


    def distanceVector(index: Int, connectedTo:Int): Vector3 = {
      val xDis = Math.sqrt(Math.pow((physicVertex(connectedTo).position.clone().x - physicVertex(index).position.clone().x), 2))
      val yDis = Math.sqrt(Math.pow((physicVertex(connectedTo).position.clone().y - physicVertex(index).position.clone().y), 2))
      val zDis = Math.sqrt(Math.pow((physicVertex(connectedTo).position.clone().z - physicVertex(index).position.clone().z), 2))
      new Vector3(xDis, yDis, zDis)

    }

    def initDistances() = {
      for ((v, index) <- physicVertex.zipWithIndex) {
        v.initialDistances = distanceVector(index, v.connectedToIndex)
      }
    }

    def initFirstPosition() = {
      for ((v, index) <- physicVertex.zipWithIndex) {
        val force = physicVertex(index).force.clone()

        val x = v.position.x + DeltaT.deltaT * DeltaT.deltaT * force.x / vertexMass
        val y = v.position.y + DeltaT.deltaT * DeltaT.deltaT * force.y / vertexMass
        val z = v.position.z + DeltaT.deltaT * DeltaT.deltaT * force.z / vertexMass
        physicVertex(index).prevPosition.x = x
        physicVertex(index).prevPosition.y = y
        physicVertex(index).prevPosition.z = z
      }
    }

    def letsGoOverEveryVertex() = {

      for ((v, index) <- physicVertex.zipWithIndex) {
        addGravitationForce(v, index)
        addSpringForceGlobal(v, index)
        calculateVerletPosition(v, index)
        colisionCheck(v, index)
        applyNewVerticesPosition(v, index)
        clearForces(v, index)
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
      val staticDistance = physicVertex(index).initialDistances
      if (connectedTo > 0) {

        val yDisPrev = Math.sqrt(Math.pow((physicVertex(connectedTo).prevPosition.clone().y - physicVertex(index).prevPosition.clone().y), 2))
        val yDis = Math.sqrt(Math.pow((physicVertex(connectedTo).position.clone().y - physicVertex(index).position.clone().y), 2))
        val yVelocity = (yDisPrev - yDis) * DeltaT.deltaT
        val calcForceY = -1 * springForce * (yDis - staticDistance.y) * (physicVertex(index).position.clone().y / yDis) - dampingCoefficient * yVelocity
        val y = calcForceY

        new Vector3(0, y, 0)
      }
      else new Vector3(0, 0, 0)


    }

    def applyNewVerticesPosition(v: PhysicVertex, index: Int) = {
        sphere.geometry.vertices.apply(index).copy(v.position)
    }


    def colisionCheck(v: PhysicVertex, index: Int) = {

      val connectedTo = physicVertex(index).connectedToIndex
      val staticDistance = physicVertex(index).initialDistances

      var position = sphere.localToWorld(physicVertex(index).position.clone())


        val yDis = Math.sqrt(Math.pow((physicVertex(connectedTo).position.clone().y - physicVertex(index).position.clone().y), 2))
        if (yDis <= (staticDistance.y*distanceTolerance) ) {
          physicVertex(index).position.y = physicVertex(index).prevPosition.clone().y
        }
        else if (position.y < 0) {
          physicVertex(index).position.y = physicVertex(index).prevPosition.clone().y
        }
       position = sphere.worldToLocal(position)
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
        val x : Double = (2 * currentPosition.x) - prevPosition.x + (DeltaT.deltaT * DeltaT.deltaT) * (force.x / vertexMass)
        val y : Double = (2 * currentPosition.y) - prevPosition.y + (DeltaT.deltaT * DeltaT.deltaT) * (force.y / vertexMass)
        val z : Double = (2 * currentPosition.z) - prevPosition.z + (DeltaT.deltaT * DeltaT.deltaT) * (force.z / vertexMass)

        physicVertex(index).prevPosition = currentPosition

        physicVertex(index).position.x = x
        physicVertex(index).position.y = y
        physicVertex(index).position.z = z
    }

  }



}
*/
