package com.filemon.threephysicscala

import physics.webapp.THREE.Math.{Vector2, Vector3}

trait Operands {

  def distanceCalc(x1: Double, x2: Double) = {
    Math.sqrt( Math.pow((x1 - x2), 2)  )
  }


  def distanceVector2(vector1: Vector2, vector2: Vector2) = {
    Math.sqrt( Math.pow((vector1.x - vector2.x), 2) + Math.pow((vector1.y - vector2.y), 2)  )
  }

  def distanceVector3(vector1: Vector3, vector2: Vector3) = {
    Math.sqrt( Math.pow((vector1.x - vector2.x), 2) + Math.pow((vector1.y - vector2.y), 2) + Math.pow((vector1.z - vector2.z), 2)   )
  }

}


/*

MIXED
   pushString(0, 7, forceMixed)
      pushString(1, 6, forceMixed)

      pushString(1, 8, forceMixed)
      pushString(2, 7, forceMixed)

      pushString(2, 9, forceMixed)
      pushString(3, 8, forceMixed)

      pushString(3, 10, forceMixed)
      pushString(4, 9, forceMixed)

      pushString(4, 11, forceMixed)
      pushString(5, 10, forceMixed)



      pushString(6, 13, forceMixed)
      pushString(7, 12, forceMixed)

      pushString(7, 14, forceMixed)
      pushString(8, 13, forceMixed)

      pushString(8, 15, forceMixed)
      pushString(9, 14, forceMixed)

      pushString(9, 16, forceMixed)
      pushString(10, 15, forceMixed)

      pushString(10, 17, forceMixed)
      pushString(11, 16, forceMixed)



      pushString(12, 19, forceMixed)
      pushString(13, 18, forceMixed)

      pushString(13, 20, forceMixed)
      pushString(14, 19, forceMixed)

      pushString(14, 21, forceMixed)
      pushString(15, 20, forceMixed)

      pushString(15, 22, forceMixed)
      pushString(16, 21, forceMixed)

      pushString(16, 23, forceMixed)
      pushString(17, 22, forceMixed)



      pushString(18, 25, forceMixed)
      pushString(19, 24, forceMixed)

      pushString(19, 26, forceMixed)
      pushString(20, 25, forceMixed)

      pushString(20, 27, forceMixed)
      pushString(21, 26, forceMixed)

      pushString(21, 28, forceMixed)
      pushString(22, 27, forceMixed)

      pushString(22, 29, forceMixed)
      pushString(23, 28, forceMixed)



      pushString(24, 31, forceMixed)
      pushString(25, 30, forceMixed)

      pushString(25, 32, forceMixed)
      pushString(26, 31, forceMixed)

      pushString(26, 33, forceMixed)
      pushString(27, 32, forceMixed)

      pushString(27, 34, forceMixed)
      pushString(28, 33, forceMixed)

      pushString(28, 35, forceMixed)
      pushString(29, 34, forceMixed)
* */



/*
//addSpringHorizontal

pushString(0, 1, forceHorizontal)
    pushString(1, 2, forceHorizontal)
    pushString(2, 3, forceHorizontal)
    pushString(3, 4, forceHorizontal)
    pushString(4, 5, forceHorizontal)

    pushString(6, 7, forceHorizontal)
    pushString(7, 8, forceHorizontal)
    pushString(8, 9, forceHorizontal)
    pushString(9, 10, forceHorizontal)
    pushString(10, 11, forceHorizontal)

    pushString(12, 13, forceHorizontal)
    pushString(13, 14, forceHorizontal)
    pushString(14, 15, forceHorizontal)
    pushString(15, 16, forceHorizontal)
    pushString(16, 17, forceHorizontal)

    pushString(18, 19, forceHorizontal)
    pushString(19, 20, forceHorizontal)
    pushString(20, 21, forceHorizontal)
    pushString(21, 22, forceHorizontal)
    pushString(22, 23, forceHorizontal)

    pushString(24, 25, forceHorizontal)
    pushString(25, 26, forceHorizontal)
    pushString(26, 27, forceHorizontal)
    pushString(27, 28, forceHorizontal)
    pushString(28, 29, forceHorizontal)

    pushString(30, 31, forceHorizontal)
    pushString(31, 32, forceHorizontal)
    pushString(32, 33, forceHorizontal)
    pushString(33, 34, forceHorizontal)
    pushString(34, 35, forceHorizontal)




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
*/