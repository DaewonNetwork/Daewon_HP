async function get1(phId){
    const result = await axios.get(`/api/review/list/${phId}`)
    console.log(result)
}

async function getReviewList({phId, page, size, goLast}){
    // console.log(bno, page, size)
    const result = await axios.get(`/api/review/list/${phId}`, {params: {page, size}})
    // console.log(result)
    if(goLast) {
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getReviewList({phId:phId, page:lastPage, size:size})
    }
    return result.data
}

async function getReplyList({reviewId}){

    const result = await axios.get(`/api/reply/list/${reviewId}`)

    return result.data
}