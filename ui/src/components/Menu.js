import { Link } from 'react-router-dom'

const Menu = () => {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/create">Create Club</Link>
                </li>
            </ul>
        </nav>
    )
}

export default Menu
