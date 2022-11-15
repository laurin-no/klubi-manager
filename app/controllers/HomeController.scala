package controllers

import play.api.libs.json.Json
import play.api.mvc._

class HomeController(cc: ControllerComponents) extends AbstractController(cc) {

  def appSummary = Action {
    Ok(Json.obj("content" -> "Scala Play React Seed!"))
  }
}
