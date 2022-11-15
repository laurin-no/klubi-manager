package persistence

import slick.basic.DatabasePublisher
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.JdbcProfile

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class ClubRepository(
    val profile: JdbcProfile,
    val db: Database,
    implicit val ec: ExecutionContext
) {
  import profile.api._

  private lazy val clubs = TableQuery[ClubTable]
  private lazy val members = TableQuery[MemberTable]

  def getAllClubs: DatabasePublisher[ClubRow] = {
    db.stream(clubs.result)
  }

  def findUsersForClub(id: UUID): Future[Seq[MemberRow]] = {
    val query = members.filter(_.clubId === id)
    db.run(query.result)
  }

  def insertOrUpdateClub(row: ClubRow): Future[Int] = {
    val action = clubs.insertOrUpdate(row)
    db.run(action)
  }

  def insertOrUpdateMembers(rows: Seq[MemberRow]): Future[Option[Int]] = {
    val action = members ++= rows
    db.run(action)
  }

  def createSeed(): Future[Unit] = {
    val clubId = UUID.randomUUID()
    val clubRow = ClubRow(clubId, "Very Strong Voimaklubi", "VSV")
    val memberRows = MemberRow(UUID.randomUUID(), "Nice Member", clubId) ::
      MemberRow(UUID.randomUUID(), "Another Member", clubId) :: Nil

    val actions = clubs.schema.create andThen
      (clubs += clubRow) andThen
      members.schema.create andThen
      (members ++= memberRows)

    db.run(actions).map(_ => ())
  }

  private class ClubTable(tag: Tag) extends Table[ClubRow](tag, "club") {

    def id = column[UUID]("id", O.PrimaryKey)
    def name = column[String]("name")
    def abbreviation = column[String]("abbreviation")

    override def * =
      (id, name, abbreviation) <> ((ClubRow.apply _).tupled, ClubRow.unapply)
  }

  private class MemberTable(tag: Tag) extends Table[MemberRow](tag, "member") {

    def id = column[UUID]("id", O.PrimaryKey)
    def name = column[String]("name")
    def clubId = column[UUID]("club_id")

    def club = foreignKey("club_fk", clubId, clubs)(_.id)

    override def * =
      (id, name, clubId) <> ((MemberRow.apply _).tupled, MemberRow.unapply)
  }
}
