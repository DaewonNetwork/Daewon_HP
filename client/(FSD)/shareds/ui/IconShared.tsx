import React from "react"
import { FcGoogle } from "react-icons/fc";
import { FaApple, FaPlus } from "react-icons/fa";
import { RiKakaoTalkFill } from "react-icons/ri";
import { FaChevronLeft, FaChevronRight, FaEye, FaEyeSlash } from "react-icons/fa6";
import { IoCloseCircle, IoPersonSharp, IoChatbubble, IoPersonCircleOutline, IoPersonCircle, IoCopy, IoChatbubbleOutline, IoCopyOutline, IoHeart, IoHeartOutline, IoSettingsSharp, } from "react-icons/io5";
import { GoHome, GoHomeFill, GoPencil } from "react-icons/go";
import { IconType } from "../types/IconType.type";
import { IconSizeClassNames } from "../classNames/customClassNames";
import { IoSearchSharp } from "react-icons/io5";

const IconShared: React.FC<IconType> = ({ iconType, size = "md", event }) => {
    let component = null;

    const iconSizeClassName = IconSizeClassNames(size);

    switch (iconType) {
        case "google":
            component = <FcGoogle />;
            break;
        case "apple":
            component = <FaApple />;
            break;
        case "kakao":
            component = <RiKakaoTalkFill />;
            break;
        case "left":
            component = <FaChevronLeft />;
            break;
        case "right":
            component = <FaChevronRight />;
            break;
        case "eye":
            component = <FaEye />;
            break;
        case "eye_active":
            component = <FaEyeSlash />;
            break;
        case "close":
            component = <IoCloseCircle />;
            break;
        case "plus":
            component = <FaPlus />;
            break;
        case "person":
            component = <IoPersonSharp />;
            break;
        case "home":
            component = <GoHome />;
            break;
        case "home_active":
            component = <GoHomeFill />;
            break;
        case "chat":
            component = <IoChatbubbleOutline />;
            break;
        case "chat_active":
            component = <IoChatbubble />;
            break;
        case "profile":
            component = <IoPersonCircleOutline />;
            break;
        case "profile_active":
            component = <IoPersonCircle />;
            break;
        case "matching":
            component = <IoCopyOutline />;
            break;
        case "matching_active":
            component = <IoCopy />;
            break;
        case "like":
            component = <IoHeartOutline />;
            break;
        case "like_active":
            component = <IoHeart />;
            break;
        case "setting":
            component = <IoSettingsSharp />;
            break;
        case "pencil":
            component = <GoPencil />;
            break;
        case "search":
            component = <IoSearchSharp />;
            break;
        default:
            component = null;
            break;
    }

    return <span onClick={event} className={iconSizeClassName}>{component}</span>
};

export default IconShared;