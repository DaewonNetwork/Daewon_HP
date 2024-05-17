import { ButtonProps } from "@nextui-org/button"
import { ReactNode } from "react"

export interface ButtonType extends ButtonProps {
    children?: ReactNode;
}