import type { PharmacyType } from "./pharmacys/Pharmacy.type";

export interface PharmacyMapType {
    pharmacyList: PharmacyType[];
    isPending: boolean;
}