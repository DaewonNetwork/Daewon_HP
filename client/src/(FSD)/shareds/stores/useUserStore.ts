import { create } from "zustand";
import { UserType } from "../types/User.type";

interface UserStateType {
    user: UserType | null;
    accessToken: string | null;
    refreshToken: string | null;
    isLoggedIn: boolean;
    setUser: (user: UserType | null) => void;
    setIsLoggedIn: (isLoggedIn: boolean) => void;
    setAccessToken: (accessToken: string | null) => void;
    setRefreshToken: (refreshToken: string | null) => void;
}

const useUserStore = create<UserStateType>((set) => ({
    user: null,
    accessToken: null,
    refreshToken: null,
    isLoggedIn: false,
    setAccessToken: (accessToken: string | null) => set({ accessToken }),
    setRefreshToken: (refreshToken: string | null) => set({ refreshToken }),
    setUser: (user: UserType | null) => set({ user }),
    setIsLoggedIn: (isLoggedIn: boolean) => set({ isLoggedIn }),
}));

export default useUserStore;