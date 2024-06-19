import { InfiniteData, QueryObserverResult, RefetchOptions } from "@tanstack/react-query";

export interface RefetchType {
    refetch: (options?: RefetchOptions | undefined) => Promise<QueryObserverResult<InfiniteData<any, unknown>, Error>>;
};