import { FetchType } from "../types/FetchData.type";


export const fetchData = async ({ path, method = "GET", contentType = "application/json", isAuthRequired = false, isNotAuthRequired = false }: FetchType) => {
    let response = null;

    const isLoggedIn: boolean = !!localStorage.getItem("access_token");

    if ((!isNotAuthRequired && isLoggedIn) || isAuthRequired) {
        response = await fetch(`http://localhost:8090/api${path}`, {
            method: method,
            headers: {
                "Content-Type": contentType,
                "Authorization": `Bearer ${localStorage.getItem("access_token")}`
            }
        });
    } else {
        response = await fetch(`http://localhost:8090${path}`, {
            headers: {
                "Content-Type": contentType,
            }
        });
    };

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    };

    const data = await response.json();

    return data;
};