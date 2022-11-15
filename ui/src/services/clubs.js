const baseUrl = '/api/clubs'

/* eslint-disable no-undef */
const getAll = async (dataHandler) => {
    const response = await fetch(baseUrl)
    const results = response.body
        .pipeThrough(new TextDecoderStream())
        .pipeThrough(
            new TransformStream({
                transform(chunk, controller) {
                    controller.enqueue(JSON.parse(chunk))
                },
            })
        )

    const reader = results.getReader()

    const handleReader = async (reader) => {
        const { value, done } = await reader.read()
        if (!done) {
            dataHandler(value)
            await handleReader(reader)
        }
    }

    await handleReader(reader)
}

export default { getAll }
