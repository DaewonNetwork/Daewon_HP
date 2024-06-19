import { useRouter } from "next/navigation";
import { useEffect } from "react";

export const useMapLoad = () => {
    const router = useRouter();

    useEffect(() => {
        const loadKakaoMap = () => {
            (window as any).kakao.maps.load(() => {
                router.refresh();
            });
        };

        if (typeof (window as any).kakao === "undefined" || typeof (window as any).kakao.maps === "undefined") {
            const script = document.createElement("script");
            script.onload = loadKakaoMap;
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=2394155f8d491f33d2e132cb5633c658&libraries=services&autoload=false";
            document.head.appendChild(script);
        } else {
            loadKakaoMap();
        }
    }, []);
};