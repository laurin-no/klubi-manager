import { configureStore } from '@reduxjs/toolkit'
import clubReducer from './reducers/clubReducer'

const store = configureStore({
    reducer: {
        clubs: clubReducer,
    },
})

export default store
