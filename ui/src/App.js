import React, { useEffect } from 'react'

import './App.css'
import { Route, Routes } from 'react-router-dom'
import ClubList from './components/ClubList'
import { useDispatch } from 'react-redux'
import { initializeClubs } from './reducers/clubReducer'
import ClubForm from './components/ClubForm'
import Menu from './components/Menu'

const App = () => {
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(initializeClubs())
    }, [dispatch])

    return (
        <div>
            <Menu />
            <h2>Klubi Manager</h2>
            <Routes>
                <Route path="/" element={<ClubList />} />
                <Route path="/create" element={<ClubForm />} />
            </Routes>
        </div>
    )
}

export default App
