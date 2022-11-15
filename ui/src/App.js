import React from 'react'

import './App.css'
import { Route, Routes } from 'react-router-dom'
import ClubList from './components/ClubList'

const App = () => {
    return (
        <div>
            <h2>Klubi Manager</h2>
            <Routes>
                <Route path="/" element={<ClubList />} />
            </Routes>
        </div>
    )
}

export default App
