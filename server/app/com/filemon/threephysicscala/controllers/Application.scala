package com.filemon.threephysicscala.controllers

import javax.inject._

import com.filemon.threephysicscala.shared.SharedMessages
import play.api.mvc._

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

  def spring = Action {
    Ok(views.html.spring(SharedMessages.itSpringWorks))
  }

}
