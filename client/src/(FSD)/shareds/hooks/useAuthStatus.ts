import { UserType } from '@/(FSD)/shareds/types/User.type';
import { useUserStore } from '@/(FSD)/shareds/stores/useUserStore';
import { useUserRead } from '@/(FSD)/entities/user/api/useUserRead';
"use client";

import { useEffect } from "react";

const useAuthStatus = () => {
    const { data, isError, isPending, refetch } = useUserRead();

    const { setUser, setIsLoggedIn, setAccessToken, setRefreshToken } = useUserStore();

    const user: UserType = data;

    useEffect(() => {
        if(user) {
            setUser(user);
            setIsLoggedIn(true);
            setAccessToken(localStorage.getItem("access_token"));
            setRefreshToken(localStorage.getItem("refresh_token"));
        } else {
            setUser(null);
            setIsLoggedIn(false);
            setAccessToken(null);
            setRefreshToken(null);
        }
    }, [data]);

    useEffect(() => {
        if (isError) {
            localStorage.removeItem("access_token");
            localStorage.removeItem("refresh_token");
        }
    }, [isError]);

    useEffect(() => {
        refetch();
    }, [localStorage.getItem("access_token")]);

    return { isPending };
};

export default useAuthStatus;