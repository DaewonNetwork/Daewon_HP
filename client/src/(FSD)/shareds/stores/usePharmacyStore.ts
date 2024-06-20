import { create } from "zustand";
import { PharmacyType } from "../types/pharmacys/Pharmacy.type";

interface PharmacyStateType {
    pharmacyAllList: PharmacyType[] | null;
    pharmacyKeywordList: PharmacyType[] | null;
    pharmacyNearList: PharmacyType[] | null;
    pharmacyRankEnjoyListRead: PharmacyType[] | null;
    pharmacyRankStarList: PharmacyType[] | null;
    pharmacyRegionKeywordList: PharmacyType[] | null;
    pharmacyRegionList: PharmacyType[] | null;
    pharmacyRead: PharmacyType[] | null;
    
    setPharmacyAllList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyKeywordList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyNearList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyRankEnjoyList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyRankStarList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyRegionKeywordList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyRegionList: (pharmacyList: PharmacyType[] | null) => void;
    setPharmacyRead: (pharmacyList: PharmacyType[] | null) => void;
}

const usePharmacyStore = create<PharmacyStateType>((set) => ({
    pharmacyAllList: null,
    pharmacyKeywordList: null,
    pharmacyNearList: null,
    pharmacyRankEnjoyListRead: null,
    pharmacyRankStarList: null,
    pharmacyRegionKeywordList: null,
    pharmacyRegionList: null,
    pharmacyRead: null,
    
    setPharmacyAllList: pharmacyList => set({ pharmacyAllList: pharmacyList }),
    setPharmacyKeywordList: pharmacyList => set({ pharmacyKeywordList: pharmacyList }),
    setPharmacyNearList: pharmacyList => set({ pharmacyNearList: pharmacyList }),
    setPharmacyRankEnjoyList: pharmacyList => set({ pharmacyRankEnjoyListRead: pharmacyList }),
    setPharmacyRankStarList: pharmacyList => set({ pharmacyRankStarList: pharmacyList }),
    setPharmacyRegionKeywordList: pharmacyList => set({ pharmacyRegionKeywordList: pharmacyList }),
    setPharmacyRegionList: pharmacyList => set({ pharmacyRegionList: pharmacyList }),
    setPharmacyRead: pharmacyList => set({ pharmacyRead: pharmacyList }),
}));

export default usePharmacyStore;