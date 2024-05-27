async function get1(phId){
    const result = await axios.get(`/review/list/${phId}`)
    console.log(result)
}