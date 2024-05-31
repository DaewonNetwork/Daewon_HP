import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import ModalShared from "@/(FSD)/shareds/ui/ModalShared";
import AllMap from "@/(FSD)/widgets/map/ui/AllMap";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "HP - 전체 검색",
}

const Page = () => {
    return (
        <>
            <MapSearchForm />
            <AllMap />
            <ModalShared>
                <AllPharmacyList />
            </ModalShared>
        </>
    );
};

export default Page;