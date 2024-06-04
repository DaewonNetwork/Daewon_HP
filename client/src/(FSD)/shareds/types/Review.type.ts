export interface ReviewType {
    review: boolean;
    reviewId: number;
    phName: string;
    userName: string;
    reviewTitle: string;
    reviewText: string;
    likeIndex: number;
    star: number;
    createAt: string;
    updateAt: string;
    reviewImage: Uint8Array;
}
/*
 
 private Long reviewId;
    private String phName;
    private String userName;

    @NotEmpty
    private String reviewText;

    private int star;

    private int likeIndex;
    private int replyIndex;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private List<ReviewImageDTO> reviewImages; // 리뷰 이미지 리스트
*/