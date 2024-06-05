export interface ReviewType {
    review: boolean;
    reviewId: number;
    phName: string;
    userName: string;
    reviewTitle: string;
    reviewText: string;
    star: number;
    createAt: string;
    updateAt: string;
    reviewImage: Uint8Array;
}
