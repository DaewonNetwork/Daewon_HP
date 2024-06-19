"use client";

import { useUserRead } from "@/(FSD)/entities/user/api/useUserRead";
import useUserStore from "@/(FSD)/shareds/stores/useUserStore";
import { UserType } from "@/(FSD)/shareds/types/User.type";
import { useEffect } from "react";

const useAuthStatus = () => {
    const { data, isSuccess, isError } = useUserRead();
    const { setUser, setIsLoggedIn, setAccessToken, setRefreshToken } = useUserStore();

    const user: UserType = data;

    useEffect(() => {
        if (isSuccess && data) {
            setUser(user);
            setIsLoggedIn(true);
            setAccessToken(localStorage.getItem("access_token"));
            setRefreshToken(localStorage.getItem("refresh_token"));
        } else if (isError) {
            setUser(null);
            setIsLoggedIn(false);
            setAccessToken(null);
            setRefreshToken(null);
        }
    }, [isSuccess, isError, data, setUser, setIsLoggedIn, setAccessToken, setRefreshToken]);
};

export default useAuthStatus;