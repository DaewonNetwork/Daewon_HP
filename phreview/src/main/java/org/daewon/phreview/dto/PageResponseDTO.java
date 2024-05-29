package org.daewon.phreview.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int pageIndex;
    private int size;
    private int totalPageIndex;
    private int totalIndex;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<E> phList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> phList, int totalIndex) {
        if(totalIndex <= 0) {
            return;
        }
        this.pageIndex = pageRequestDTO.getPageIndex();
        this.size = pageRequestDTO.getSize();
        this.totalIndex = totalIndex;
        this.totalPageIndex = (int)(Math.ceil((double)totalIndex / size)); // 전체 페이지 수 계산

        this.phList = phList;

        this.end = Math.min((int)(Math.ceil((double)(pageIndex + 1) / 10.0)) * 10, totalPageIndex); // 화면에 표시할 페이지번호 갯수...
        this.start = this.end - 9; // 화면에서 시작번호
        this.prev = this.start > 1;
        this.next = totalPageIndex > this.end;
    }
}


