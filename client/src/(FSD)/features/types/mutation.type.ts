export interface MutationType {
    onSuccess: (data: any) => void;
    onError?: () => void;
}