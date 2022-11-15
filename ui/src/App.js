import React from 'react'

import './App.css'
import { useSelector } from 'react-redux'

const App = () => {
    // const useEffect
    const clubs = useSelector((state) => state.clubs)

    return <div>Hello World</div>
}

export default App
