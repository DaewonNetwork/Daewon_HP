import UserReplyList from "@/(FSD)/entities/user/ui/UserReplyList";
import UserReviewList from "@/(FSD)/entities/user/ui/UserReviewList";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import React from "react";

const UserProfileContainer = () => {
    return (
        <InnerShared>
            <UserReviewList />
            <UserReplyList />
        </InnerShared>
    );
};

export default UserProfileContainer;