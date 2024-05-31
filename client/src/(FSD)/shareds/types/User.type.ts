export interface UserType {
    password: string;
    email: string;
    userId?: number;
    userName?: string;
    exp?: number;
    iat?: number;
    roles?: string[];
};