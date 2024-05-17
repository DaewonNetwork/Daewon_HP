import { DomCommonType } from "./DomCommonType.type";

export interface IconType extends DomCommonType {
    iconType: 
    "google" | "apple" | "kakao" | "right" | "left" | "eye" | "eye_active" | "close" | "plus" | "person" | "home" | "home_active" |
    "chat" | "chat_active" | "profile" | "profile_active" |
    "matching" | "matching_active" | "like" | "like_active" | 
    "setting" | "pencil" | "search";
    onClick?: () => void;
}