export interface PharmacyType {
    phId: number; // 병원 아이디
    phName: string; // 병원 이름
    phTel: string; // 병원 전화번호
    phAdd: string; // 병원 주소
    timeWeekStartDate: string; // 평일 시작
    timeWeekEndDate: string; // 평일 종료
    timeSatStartDate: string; // 토요일 시작
    timeSatEndDate: string; // 토요일 종료
    timeHoliStartDate: string; // 공휴일 시작
    timeHoliEndDate: string; // 공휴일 종료
    phX: number;  // 경도
    phY: number;  // 위도
    enjoyIndex: number; // 즐겨찾기 인덱스
    starIndex: number; // 별점 인덱스
}