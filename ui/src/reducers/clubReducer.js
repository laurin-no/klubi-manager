import { createSlice } from '@reduxjs/toolkit'
// import clubService from '../services/clubs'

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

// export const initializeClubs = () => {
//   return async (dispatch) => {
//     const clubs = await clubService.getAll()
//     dispatch()
//   }
// }

export default clubSlice.reducer
