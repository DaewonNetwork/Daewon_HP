<!--#MIMETYPE application/javascript -->

async function get1(phId) {  // async -> 비동기 처리 함수 명시
    const result = await axios.get(`/api/review/list/${phId}`);     // await -> 비동기 호출
    // console.log(result);
    // console.log(result.data);
    return result.data;
}

async function getList({phId})   {   // goLast는 강제로 마지막 댓글로 가게 하는 얘 본인이 작성하자마자 댓글을 보기 위해 추가
    const result = await axios.get(`/api/review/list/${phId}`)
    console.log(result)
    console.log(result.data) // result에는 config, data, header, request...등이 있음.
    return result.data
}

async function addReply(reviewObj) {     // 댓글 등록
    const response = await axios.post(`/api/review/`, reviewObj)
    return response.data
}

async function getReview(reviewId) {
    const response = await axios.get(`/api/review/${reviewId}`) // 댓글 조회
    console.log(response)
    return response.data
}

async function modifyReply(reviewObj) {
    const response = await axios.put(`/api/review/${reviewObj.reviewId}`, reviewObj)  // 댓글 수정정보를 전달
    return response.data
}

// 댓글 삭제
async function removeReply(reviewId) {
    const response = await axios.delete(`/api/review/${reviewId}`)
    return response.data
}
