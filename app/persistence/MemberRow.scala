package persistence

import java.util.UUID

case class MemberRow(
    id: UUID,
    name: String,
    clubId: UUID
)
