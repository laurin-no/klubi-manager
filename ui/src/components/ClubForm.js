import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { createClub } from '../reducers/clubReducer'

const ClubForm = () => {
    const [clubName, setClubName] = useState('')
    const [memberName, setMemberName] = useState('')
    const [memberNames, setMemberNames] = useState([])

    const dispatch = useDispatch()

    const addClub = async (event) => {
        event.preventDefault()

        const clubObject = {
            name: clubName,
            members: memberNames.map((m) => ({ name: m })),
        }

        dispatch(createClub(clubObject))

        setClubName('')
        setMemberNames([])
    }

    const handleAddMember = (event) => {
        event.preventDefault()

        setMemberNames((prevState) => prevState.concat(memberName))
        setMemberName('')
    }

    return (
        <form onSubmit={addClub}>
            <div>
                <label>Club Name</label>
                <input
                    type="text"
                    value={clubName}
                    name="Club Name"
                    onChange={({ target }) => setClubName(target.value)}
                />
            </div>
            <div>
                <b> Members to add:</b>
                <ul>
                    {memberNames.map((m) => (
                        <li key={m}>{m}</li>
                    ))}
                </ul>
            </div>
            <div>
                <label>Member Name</label>
                <input
                    type="text"
                    value={memberName}
                    name="Member Name"
                    onChange={({ target }) => setMemberName(target.value)}
                />
                <button onClick={handleAddMember}>add Member</button>
            </div>

            <button type="submit">create</button>
        </form>
    )
}

export default ClubForm
