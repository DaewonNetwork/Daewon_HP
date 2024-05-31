export interface ReviewType {
    phId: number;
    userId: number;
    reviewText: string;
    star: number;
    createAt: Date;
    updateAt: Date;
}
/*
 
    private Long phId;      // 특정한 병원 번호를 선언
    private Long userId;    // 특정한 유저 id를 선언

    @NotEmpty
    private String reviewText;

    private int star;


    private LocalDateTime createAt;
    private LocalDateTime updateAt;
*/