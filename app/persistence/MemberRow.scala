package persistence

import model.Member

import java.util.UUID

case class MemberRow(
    id: UUID,
    name: String,
    clubId: UUID
)

object MemberRow {
  def fromMember(member: Member): MemberRow =
    MemberRow(member.id, member.name, member.clubId)
}
