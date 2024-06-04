import styles from "@/(FSD)/shareds/styles/ComponentStyle.module.scss";
import InnerShared from "@/(FSD)/shareds/ui/InnerShared";
import BackBtnShared from "@/(FSD)/shareds/ui/BackBtnShared";
import TextLargeShared from "@/(FSD)/shareds/ui/TextLargeShared";

const TitleHeader = ({ title } : { title: string }) => {
    return (
        <div className={styles.title_header}>
            <InnerShared>
                <BackBtnShared />
                <TextLargeShared>{title}</TextLargeShared>
            </InnerShared>
        </div>
    );
};

export default TitleHeader;