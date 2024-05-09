async function get1(bno) {  // async -> 비동기 처리 함수 명시
    const result = await axios.get(`/replies/list/${bno}`);     // await -> 비동기 호출
    // console.log(result);
    // console.log(result.data);
    return result.data;
}

async function getList({bno, page, size, goLast})   {   // goLast는 강제로 마지막 댓글로 가게 하는 얘 본인이 작성하자마자 댓글을 보기 위해 추가
    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})   // params으로 page, size 파라미터값을 집어 넣겠다
    console.log(result.data) // result에는 config, data, header, request...등이 있음.

    if(goLast) {    // 강제로 마지막 댓글로, boolean타입
        const total = result.data.total  // result안에 있는 data의 total을 불러옴
        const lastPage = parseInt(Math.ceil(total/size))    // int로 parse하는 이유는 문자열값으로 페이지 값이 넘어오기 떄문
        return getList({bno:bno, page:lastPage, size:size}) // JSON의 key:value 형태
    }

    return result.data
}

async function addReply(replyObj) {     // 댓글 등록
    const response = await axios.post(`/replies/`, replyObj)
    return response.data
}

async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`) // 댓글 조회
    console.log(response)
    return response.data
}

async function modifyReply(replyObj) {
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj)  // 댓글 수정정보를 전달
    return response.data
}

// 댓글 삭제
async function removeReply(rno) {
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}
