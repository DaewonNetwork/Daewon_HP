"use client";

import { useReadUser } from "@/(FSD)/entities/user/api/useReadUser";
import { useRouter } from "next/navigation";
import { useEffect } from "react";
import useUserStore from "../stores/useUserStore";

const useIsLoggedIn = (isPrivate: boolean) => {
    const { setUser } = useUserStore();
    const router = useRouter();

    const { data } = useReadUser();

    useEffect(() => {
        if (data) {
            setUser(data);
        } else if (isPrivate) {
            router.push("/auth/signin");
        }
    }, []);

};

export default useIsLoggedIn;