package physics.webapp.Physics

trait Constants {

  var EARTH_G = 9.81 //m/s^2
  var deltaT = 0.009
  val earthMass = 50//5.97219 * Math.pow(10,24) // kg
  val earthRadius = 6371.008 //km

  val sunMass = 5000//1.98855 * Math.pow(10,30) // kg
  val smallSunMall = 5000/2//1.98855 * Math.pow(10,30) // kg
  val sun3Mass = sunMass
  val sunRadius = 696342 //km


}

object DeltaT {
  var windForce = 0.03
  var deltaT = 0.02
  var gravity = 5.0
}
