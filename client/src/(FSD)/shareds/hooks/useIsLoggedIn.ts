"use client";

import { useReadUser } from "@/(FSD)/entities/user/api/useReadUser";
import { useRouter } from "next/navigation";
import { useEffect } from "react";
import useUserStore from "../stores/useUserStore";

const useIsLoggedIn = (isPrivate: boolean) => {
    const { setUser, setIsLoggedIn } = useUserStore();
    const router = useRouter();

    const { data } = useReadUser();

    useEffect(() => {
        if(!data) {
            setUser(null);
            setIsLoggedIn(false);
            return;
        }
        if (data) {
            setUser(data);
            setIsLoggedIn(true);
        } else if (isPrivate) {
            router.push("/auth/signin");
        }
    }, [data, isPrivate, localStorage]);

};

export default useIsLoggedIn;