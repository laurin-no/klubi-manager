package service

import akka.NotUsed
import akka.stream.scaladsl.Source
import model.{Club, ClubRequest, Member}
import persistence.{ClubRepository, ClubRow, MemberRow}

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class ClubService(clubRepository: ClubRepository)(implicit
    ec: ExecutionContext
) {

  def getClubs: Source[Club, NotUsed] = {
    Source
      .fromPublisher(clubRepository.getAllClubs)
      .mapAsync(1) { clubRow =>
        for {
          rows <- clubRepository.findUsersForClub(clubRow.id)
          members = rows.map(mapToMember)
        } yield Club(clubRow.id, clubRow.name, members, clubRow.abbreviation)
      }
  }

  def createClub(clubRequest: ClubRequest): Future[Club] = {
    val clubId = UUID.randomUUID()
    val members =
      clubRequest.members.map(m => Member(UUID.randomUUID(), m.name, clubId))
    val abbreviation = buildAbbreviation(clubRequest.name)
    val club = Club(clubId, clubRequest.name, members, abbreviation)

    val clubRow = ClubRow.fromClub(club)
    val memberRows = club.members.map(MemberRow.fromMember)

    for {
      _ <- clubRepository.insertOrUpdateClub(clubRow)
      _ <- clubRepository.insertOrUpdateMembers(memberRows)
    } yield club
  }

  private def mapToMember(row: MemberRow) = {
    Member(row.id, row.name, row.clubId)
  }

  private def buildAbbreviation(clubName: String): String = {
    val nameParts = clubName.split(" ").toList

    def formatString(list: List[String], length: Int) =
      list.map(_.take(length)).mkString.toUpperCase

    nameParts match {
      case _ :: Nil      => formatString(nameParts, 3)
      case _ :: _ :: Nil => formatString(nameParts, 2)
      case _ :: _ :: _   => formatString(nameParts, 1)
      case Nil           => ""
    }
  }
}
