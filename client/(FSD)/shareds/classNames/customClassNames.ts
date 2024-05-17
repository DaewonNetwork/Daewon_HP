import classNames from "classnames/bind";
import styles from "@/(FSD)/shareds/styles/rootClassNames.module.scss";

const cn = classNames.bind(styles);

export const SizeClassNames = (size: "lg" | "md" | "sm" | undefined) => {
    const className = cn({
        "height-lg": size === "lg",
        "height-md": size === "md",
        "height-sm": size === "sm",
        "width-lg": size === "lg",
        "width-md": size === "md",
        "width-sm": size === "sm",
    });

    return className;
};

export const TextSizeClassNames = (size: "lg" | "md" | "sm" | undefined) => {
    const className = cn({
        "text-medium": (size === "lg" || size === "md"),
        "text-small": size === "sm",
    });

    return className;
};

export const IconSizeClassNames = (size: "lg" | "md" | "sm" | undefined) => {
    const className = cn({
        "text-large": size === "lg",
        "text-medium": size === "md",
        "text-small": size === "sm",
    });

    return className;
};

export const SizeFullClassNames = ({ isHeight, isWidth }: { isHeight: boolean, isWidth: boolean }) => {
    const className = cn({
        "width-full": isWidth,
        "height-full": isHeight,
    });

    return className;
};

export const RadiusClassNames = (radius: "none" | "sm" | "md" | "lg" | "full" | undefined) => {
    const className = cn({
        "rounded-full": radius === "full",
        "rounded-large": radius === "lg",
        "rounded-medium": radius === "md",
        "rounded-small": radius === "sm",
        "rounded-none": radius === "none",
    });

    return className;
};

export const FullRadiusButtonClassNames = (isIconOnly: boolean, is_w_or_h : boolean, size: "lg" | "md" | "sm" | undefined) => {
    const className = cn({
        "height-w-lg": ((isIconOnly) && (is_w_or_h) && size === "lg"),
        "height-w-md": ((isIconOnly) && (is_w_or_h) && size === "md"),
        "height-w-sm": ((isIconOnly) && (is_w_or_h) && size === "sm"),
        "width-h-lg": ((isIconOnly) && (!is_w_or_h) && size === "lg"),
        "width-h-md": ((isIconOnly) && (!is_w_or_h) && size === "md"),
        "width-h-sm": ((isIconOnly) && (!is_w_or_h) && size === "sm"),
    });

    return className;
};

export const IconOnlyButtonClassNames = (isIconOnly : boolean, size: "lg" | "md" | "sm" | undefined) => {
    const className = cn({
        "pad-lg": (isIconOnly && size === "lg"),
        "pad-md": (isIconOnly && size === "md"),
        "pad-sm": (isIconOnly && size === "sm"),
    });

    return className;
}