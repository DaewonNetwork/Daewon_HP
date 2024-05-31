import React from "react"
import { PharmacyType } from "../types/pharmacys/Pharmacy.type";
import { useRouter } from "next/navigation";

const PharmacyShared = ({ pharmacy }: { pharmacy: PharmacyType}) => {
    if(!pharmacy) return;
    const router = useRouter();
    return (
        <div
        onMouseOver={e => { e.currentTarget.classList.add("bg-content2"); }}
        onMouseOut={e => { e.currentTarget.classList.remove("bg-content2"); }}
        onClick={_ => {
            router.push(`/pharmacy/${pharmacy.phId}`);
        }}
        >
            <h1 className={"text-large font-medium"}>{ pharmacy.phName }</h1>
            <p className={`text-medium`}>{ pharmacy.phAdd }</p>
            <p className={"text-medium"}>{ pharmacy.phTel }</p>
        </div>
    )
}

export default PharmacyShared;