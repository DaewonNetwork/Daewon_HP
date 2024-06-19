import { create } from "zustand";
import { RefetchType } from "../types/Refetch.type";

interface PharmacyStateType {
    pharmacyAllSearchRefetch: RefetchType | null;
    pharmacyKeywordSearchRefetch: RefetchType | null;
    pharmacyNearSearchRefetch: RefetchType | null;
    pharmacyRankEnjoyListReadRefetch: RefetchType | null;
    pharmacyRankStarListRefetch: RefetchType | null;
    pharmacyRegionKeywordSearchRefetch: RefetchType | null;
    pharmacyRegionSearchRefetch: RefetchType | null;
    pharmacyReadRefetch: RefetchType | null;
    
    setPharmacyAllSearchRefetch: (refetch: RefetchType | null) => void;
    setPharmacyKeywordSearchRefetch: (refetch: RefetchType | null) => void;
    setPharmacyNearSearchRefetch: (refetch: RefetchType | null) => void;
    setPharmacyRankEnjoyListReadRefetch: (refetch: RefetchType | null) => void;
    setPharmacyRankStarListRefetch: (refetch: RefetchType | null) => void;
    setPharmacyRegionKeywordSearchRefetch: (refetch: RefetchType | null) => void;
    setPharmacyRegionSearchRefetch: (refetch: RefetchType | null) => void;
    setPharmacyReadRefetch: (refetch: RefetchType | null) => void;
}

const usePharmacyStore = create<PharmacyStateType>((set) => ({
    pharmacyAllSearchRefetch: null,
    pharmacyKeywordSearchRefetch: null,
    pharmacyNearSearchRefetch: null,
    pharmacyRankEnjoyListReadRefetch: null,
    pharmacyRankStarListRefetch: null,
    pharmacyRegionKeywordSearchRefetch: null,
    pharmacyRegionSearchRefetch: null,
    pharmacyReadRefetch: null,
    
    setPharmacyAllSearchRefetch: (refetch) => set({ pharmacyAllSearchRefetch: refetch }),
    setPharmacyKeywordSearchRefetch: (refetch) => set({ pharmacyKeywordSearchRefetch: refetch }),
    setPharmacyNearSearchRefetch: (refetch) => set({ pharmacyNearSearchRefetch: refetch }),
    setPharmacyRankEnjoyListReadRefetch: (refetch) => set({ pharmacyRankEnjoyListReadRefetch: refetch }),
    setPharmacyRankStarListRefetch: (refetch) => set({ pharmacyRankStarListRefetch: refetch }),
    setPharmacyRegionKeywordSearchRefetch: (refetch) => set({ pharmacyRegionKeywordSearchRefetch: refetch }),
    setPharmacyRegionSearchRefetch: (refetch) => set({ pharmacyRegionSearchRefetch: refetch }),
    setPharmacyReadRefetch: (refetch) => set({ pharmacyReadRefetch: refetch }),
}));

export default usePharmacyStore;