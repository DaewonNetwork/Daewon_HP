import { useMapCategory } from './map/api/useMapCategory';
import { useMapSearch } from '@/(FSD)/entities/map/api/useMapSearch';
import PharmacyListEntitie from './map/ui/PharmacyListEntitie';
const Entitie = {
    api: {
        map: {
            useMapSearch,
            useMapCategory,
        }
    },
    ui: {
        PharmacyList: PharmacyListEntitie,
    }
};

export default Entitie;