package persistence

import model.{Club, Member}
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.JdbcProfile

import java.util.UUID
import scala.concurrent.Future

class ClubRepository(val profile: JdbcProfile, val db: Database) {
  import profile.api._

  lazy val clubs = TableQuery[ClubTable]
  lazy val members = TableQuery[MemberTable]

  def getAllClubs() = {
    db.stream(clubs.result)
  }

  def findUsersForClub(id: UUID): Future[Seq[MemberRow]] = {
    val query = members.filter(_.clubId === id)
    db.run(query.result)
  }

  def insertOrUpdateClub(row: ClubRow) = {
    val action = clubs.insertOrUpdate(row)
    db.run(action)
  }

  def insertOrUpdateMember(row: MemberRow) = {
    val action = members.insertOrUpdate(row)
    db.run(action)
  }

  class ClubTable(tag: Tag) extends Table[ClubRow](tag, "club") {

    def id = column[UUID]("id", O.PrimaryKey)
    def name = column[String]("name")
    def abbreviation = column[String]("abbreviation")

    override def * =
      (id, name, abbreviation) <> (ClubRow.tupled, ClubRow.unapply)
  }

  private class MemberTable(tag: Tag) extends Table[MemberRow](tag, "club") {

    def id = column[UUID]("id", O.PrimaryKey)
    def name = column[String]("name")
    def clubId = column[UUID]("club_id")

    def club = foreignKey("club_fk", clubId, clubs)(_.id)

    override def * =
      (id, name, clubId) <> (MemberRow.tupled, MemberRow.unapply)
  }

}
