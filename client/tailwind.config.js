import { nextui } from "@nextui-org/theme";

/** @type {import('tailwindcss').Config} */
const config = {
    content: [
        "./app/**/*.{js,ts,jsx,tsx,mdx}",
        "./components/**/*.{js,ts,jsx,tsx,mdx}",
        "./src/**/*.{js,ts,jsx,tsx,mdx}",
        "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}"
    ],
    theme: {
        extend: {},
    },
    darkMode: "class",
    plugins: [nextui({
        themes: {
            light: {
                colors: {}
            },
            dark: {
                colors: {}
            }
        },
        layout: {
            radius: {
                small: "5px",
                medium: "8px",
                large: "20px",
            },
            fontSize: {
                large: "16px",
                medium: "14px",
                small: "12px",
                tiny: "13px",
            },
            height: {
                lg: "50px",
                md: "45px",
                sm: "42px",
            },
            width: {
                lg: "100px",
                md: "80px",
                sm: "65px",
            }
        },
        themes: ""
    })]
}

export default config;