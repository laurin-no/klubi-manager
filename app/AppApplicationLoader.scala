import com.softwaremill.macwire._
import controllers.HomeController
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import router.Routes

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

  // controllers
  lazy val homeController = wire[HomeController]
}
