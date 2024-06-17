export interface MapType {
    onCreate: ((map: kakao.maps.Map) => void) | undefined;
    children: React.ReactNode;
    onClick?: any;
}