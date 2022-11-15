import com.softwaremill.macwire._
import controllers.ClubController
import persistence.ClubRepository
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import router.Routes
import service.ClubService
import slick.jdbc.JdbcBackend.Database

class AppApplicationLoader extends ApplicationLoader {

  def load(context: Context): Application = {
    new AppComponents(context).application
  }
}

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context) {

  override lazy val httpFilters = Nil

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

  // database config
  private implicit val db = Database.forConfig("h2_db")
  private implicit val profile = slick.jdbc.H2Profile

  // repositories
  lazy val clubRepository = wire[ClubRepository]

  // services
  lazy val clubService = wire[ClubService]

  // controllers
  lazy val clubController = wire[ClubController]
}
