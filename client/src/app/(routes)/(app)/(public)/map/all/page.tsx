import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import MapSearchForm from "@/(FSD)/features/pharmacy/ui/MapSearchForm";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import MapSearchRegion from "@/(FSD)/features/pharmacy/ui/MapSearchRegion";
import SectionShared from "@/(FSD)/shareds/ui/SectionShared";
import FooterShared from "@/(FSD)/shareds/ui/FooterShared";
import AppFooter from "@/(FSD)/widgets/app/ui/AppFooter";

export const metadata: Metadata = {
    title: "HP - 전체 검색",
}

const Page = () => {
    return (
        <>
            <HeaderShared>
                <AppHeader>
                    <MapSearchForm />
                    <MapSearchRegion />
                </AppHeader>
            </HeaderShared>
            <SectionShared>
                <AllPharmacyList />
            </SectionShared>
            <FooterShared>
                <AppFooter />
            </FooterShared>
        </>
    );
};

export default Page;