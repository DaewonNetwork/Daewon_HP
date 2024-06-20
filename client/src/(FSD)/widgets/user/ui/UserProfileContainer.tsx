import UserReplyList from "@/(FSD)/entities/user/ui/UserReplyList";
import UserReviewList from "@/(FSD)/entities/user/ui/UserReviewList";
import React from "react";

const UserProfileContainer = () => {
    return (
        <>
            <UserReviewList />
            <UserReplyList />
        </>
    );
};

export default UserProfileContainer;