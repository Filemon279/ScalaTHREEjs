package com.filemon.threephysicscala.datgui

import scala.scalajs.js

@js.native
@js.annotation.JSGlobal("GUIController")
class GUIController extends js.Object {
  def destroy(): Unit = js.native
  // Controller
  var onChange: js.Function1[js.Any, Unit] = js.native
  var onFinishChange: js.Function1[js.Any, Unit] = js.native
  def setValue(value: js.Any): GUIController = js.native
  def getValue(): js.Dynamic = js.native
  def updateDisplay(): Unit = js.native
  def isModified(): Boolean = js.native
  // NumberController
  def min(n: Double): GUIController = js.native
  def max(n: Double): GUIController = js.native
  def step(n: Double): GUIController = js.native
  // FunctionController
  def fire(): GUIController = js.native
  // augmentController in dat/gui/GUI.js
  def options(option: js.Any): GUIController = js.native
  def name(s: String): GUIController = js.native
  def listen(): GUIController = js.native
  def remove(): GUIController = js.native
}