import AllPharmacyList from "@/(FSD)/widgets/pharmacy/ui/AllPharmacyList";
import PharmacyKeywordForm from "@/(FSD)/features/pharmacy/ui/PharmacyKeywordForm";
import { Metadata } from "next";
import HeaderShared from "@/(FSD)/shareds/ui/HeaderShared";
import AppHeader from "@/(FSD)/widgets/app/ui/AppHeader";
import PharmacyRegionBar from "@/(FSD)/features/pharmacy/ui/PharmacyRegionBar";
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
                    <PharmacyKeywordForm />
                    <PharmacyRegionBar />
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