const Club = ({ club }) => {
    return (
        <div>
            <h3>
                {club.name} ({club.abbreviation})
            </h3>
            <b>Members:</b>
            <ul>
                {club.members.map((member) => (
                    <li key={member.id}>{member.name}</li>
                ))}
            </ul>
        </div>
    )
}

export default Club
