package controllers

import model._
import play.api.libs.json.{JsValue, Json, Reads, Writes}
import play.api.mvc._
import service.ClubService

import scala.concurrent.ExecutionContext

class ClubController(cc: ControllerComponents, clubService: ClubService)(
    implicit val ec: ExecutionContext
) extends AbstractController(cc) {

  private implicit val memberRequestReads: Reads[MemberRequest] =
    Json.reads[MemberRequest]
  private implicit val clubRequestReads: Reads[ClubRequest] =
    Json.reads[ClubRequest]

  private implicit val memberWrites: Writes[Member] = Json.writes[Member]
  private implicit val clubWrites: Writes[Club] = Json.writes[Club]
  private implicit val clubsWrites: Writes[Clubs] = Json.writes[Clubs]

  def listClubs: Action[AnyContent] = Action {
    Ok.chunked(clubService.getClubs.map(res => Json.toJson(res)))
  }

  def createClub(): Action[JsValue] = Action.async(parse.json) { request =>
    val req = request.body.as[ClubRequest]

    clubService.createClub(req).map(res => Created(Json.toJson(res)))
  }
}
