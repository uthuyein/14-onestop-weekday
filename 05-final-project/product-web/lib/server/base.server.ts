import 'server-only';

export async function request(path:string,init:RequestInit={}) {
    const endpoint = `${process.env.REST_API_URL}/${path}`
    console.log( "Rest end point ::::::: "+endpoint)
    const response = await fetch(endpoint, init)

    if(!response.ok) {
        const messages = await response.json() as string[]
       throw  JSON.stringify({
        type:"Client Error",
        messages: messages
       })
    }
    return response
}
