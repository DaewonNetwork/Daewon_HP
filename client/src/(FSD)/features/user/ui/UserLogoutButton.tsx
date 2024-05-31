"use client";

import { Button } from "@nextui-org/button";
import React from "react";
import { useUserLogout } from "../api/useUserLogout";
import { useRouter } from "next/navigation";

const UserLogoutButton = () => {
    const router = useRouter();

    const onSuccess = () => {
        localStorage.removeItem("access_token");
        localStorage.removeItem("refresh_token");

        router.push("/");
    }
    
    const { mutate } = useUserLogout({ onSuccess });

    const onClick = () => {        
        mutate();
    };


    return (
        <Button onClick={onClick}>로그아웃</Button>
    );
};

export default UserLogoutButton;