import { createSlice } from '@reduxjs/toolkit'
import clubService from '../services/clubs'

const clubSlice = createSlice({
    name: 'clubs',
    initialState: [],
    reducers: {
        appendClub(state, action) {
            return state.concat(action.payload)
        },
    },
})

export const { appendClub } = clubSlice.actions

export const initializeClubs = () => {
    return async (dispatch) => {
        const dataHandler = (data) => {
            dispatch(appendClub(data))
        }

        await clubService.getAll((data) => dataHandler(data))
    }
}

export const createClub = (club) => {
    return async (dispatch) => {
        const newClub = await clubService.create(club)
        dispatch(appendClub(newClub))
    }
}

export default clubSlice.reducer
