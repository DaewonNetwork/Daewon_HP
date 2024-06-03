import { create } from "zustand";
import { UserType } from "../types/User.type";

interface UserStateType {
    user: UserType | null;
    isLoggedIn: boolean;
    setUser: (user: UserType | null) => void;
    setIsLoggedIn: (isLoggedIn: boolean) => void;
}

const useUserStore = create<UserStateType>((set) => ({
    user: null,
    isLoggedIn: false,
    setUser: (user: UserType | null) => set({ user }),
    setIsLoggedIn: (isLoggedIn: boolean) => set({ isLoggedIn }),
}));

export default useUserStore;