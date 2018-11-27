package com.filemon.threephysicscala.datgui
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

import scala.scalajs.js
import org.scalajs.dom.raw._
import scala.scalajs.js.|

@js.native
@js.annotation.JSGlobal("dat.GUI")
class GUI protected () extends js.Object {
  def this(option: js.Dynamic) = this()
  var __controllers: js.Array[GUIController] = js.native
  var __folders: js.Array[GUI] = js.native
  var domElement: HTMLElement = js.native
  def add(target: Object, propName: String): GUIController = js.native
  def add(target: Object, propName: String, min: Double, max: Double): GUIController = js.native
  def add(target: Object, propName: String, status: Boolean): GUIController = js.native
  def add(target: Object, propName: String, items: js.Array[(String | Double)]): GUIController = js.native
  def add(target: Object, propName: String, items: Object): GUIController = js.native
  def addColor(target: Object, propName: String): GUIController = js.native
  def addColor(target: Object, propName: String, color: String): GUIController = js.native
  def addColor(target: Object, propName: String, rgba: js.Array[Double]): GUIController = js.native
  // rgb or rgba
  def addColor(target: Object, propName: String, hsv: js.Any): GUIController = js.native
  def remove(controller: GUIController): Unit = js.native
  def destroy(): Unit = js.native
  def addFolder(propName: String): GUI = js.native
  def open(): Unit = js.native
  def close(): Unit = js.native
  def remember(target: Object, additionalTargets: Object*): Unit = js.native
  def getRoot(): GUI = js.native
  def getSaveObject(): Object = js.native
  def save(): Unit = js.native
  def saveAs(presetName: String): Unit = js.native
  def revert(gui: GUI): Unit = js.native
  def listen(controller: GUIController): Unit = js.native
  def updateDisplay(): Unit = js.native
  // gui properties in dat/gui/GUI.js
  var parent: GUI = js.native
  var scrollable: Boolean = js.native
  var autoPlace: Boolean = js.native
  var preset: String = js.native
  var width: Double = js.native
  var name: String = js.native
  var closed: Boolean = js.native
  var load: Object = js.native
  var useLocalStorage: Boolean = js.native
}