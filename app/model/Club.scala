package model

import java.util.UUID

case class Club(
    id: UUID,
    name: String,
    members: Seq[Member],
    abbreviation: String
)
