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

export default clubSlice.reducer
