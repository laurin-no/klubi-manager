package persistence

import model.Club

import java.util.UUID

case class ClubRow(
    id: UUID,
    name: String,
    abbreviation: String
)

object ClubRow {
  def fromClub(club: Club): ClubRow =
    ClubRow(club.id, club.name, club.abbreviation)
}
