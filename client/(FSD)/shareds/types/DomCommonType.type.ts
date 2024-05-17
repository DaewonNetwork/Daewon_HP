import type { ReactNode } from "react";

export interface DomCommonType {
    children?: ReactNode;
    customtype?: string;
    className?: string;
    size?: "lg" | "md" | "sm"
    radius?: "none" | "sm" | "md" | "lg" | "full"
    fullHeight?: boolean;
    fullWidth?: boolean;
    
    event?: (e: any) => void;
    data?: ReactNode;
};