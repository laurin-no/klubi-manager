import { useSelector } from 'react-redux'
import Club from './Club'

const ClubList = () => {
    const clubs = useSelector((state) => state.clubs)

    return (
        <div>
            {clubs.map((club) => (
                <Club key={club.id} club={club} />
            ))}
        </div>
    )
}

export default ClubList
