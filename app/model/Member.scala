package model

import java.util.UUID

case class Member(
    id: UUID,
    name: String,
    clubId: UUID
)
