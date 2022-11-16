import axios from 'axios'

const baseUrl = '/api/clubs'

// the proper handling of concatenated JSON is a topic for another day
/* eslint-disable no-undef */
const getAll = async (dataHandler) => {
    const splitStream = () => {
        let buffer = ''

        return new TransformStream({
            transform(chunk, controller) {
                buffer += chunk

                buffer = buffer.replaceAll('}{', '}\n{')
                const parts = buffer.split('\n')
                parts.slice(0, -1).forEach((part) => controller.enqueue(part))
                buffer = parts[parts.length - 1]
            },
            flush(controller) {
                if (buffer) controller.enqueue(buffer)
            },
        })
    }

    const handleReader = async (reader) => {
        const { value, done } = await reader.read()
        if (!done) {
            dataHandler(value)
            await handleReader(reader)
        }
    }

    const response = await fetch(baseUrl)
    const results = response.body
        .pipeThrough(new TextDecoderStream())
        .pipeThrough(splitStream())
        .pipeThrough(
            new TransformStream({
                transform(chunk, controller) {
                    controller.enqueue(JSON.parse(chunk))
                },
            })
        )

    const reader = results.getReader()

    await handleReader(reader)
}

const create = async (newObject) => {
    const response = await axios.post(baseUrl, newObject)
    return response.data
}

export default { getAll, create }
