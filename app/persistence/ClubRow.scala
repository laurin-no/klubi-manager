package persistence

import java.util.UUID

case class ClubRow(
    id: UUID,
    name: String,
    abbreviation: String
)
