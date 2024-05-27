"use client";

import { Button } from "@nextui-org/button";
import React from "react";
import { useUserLogout } from "../api/useUserLogout";

const UserLogoutButton = () => {
    const onSuccess = (data: any) => {
        console.log(data);
        
    }

    const onError = () => {

    }
    
    const { mutate } = useUserLogout({ onSuccess, onError });

    const onClick = () => {        
        mutate();
    };


    return (
        <Button onClick={onClick}>로그아웃</Button>
    );
};

export default UserLogoutButton;