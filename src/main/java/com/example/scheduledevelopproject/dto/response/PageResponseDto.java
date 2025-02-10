package com.example.scheduledevelopproject.dto.response;

import com.example.scheduledevelopproject.entity.Schedules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/*
"last": true, // 현재 페이지가 마지막 페이지인지 여부
"totalPages": 1, // 전체 페이지 수
"totalElements": 1, // 전체 데이터 개수
"first": true, // 현재 페이지가 첫 번째 페이지인지 여부
"size": 10, // 한 페이지에 표시할 데이터 개수 (pageSize와 동일)
"number": 0, // 현재 페이지 번호 (0부터 시작, pageNumber와 동일)
"numberOfElements": 1, // 현재 페이지에서 가져온 데이터 개수
 */
@Getter
@AllArgsConstructor
public class PageResponseDto<T> {

    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;

    public PageResponseDto(Page<T> page) {
        this.content = page.getContent();
        this.page = page.getPageable().getPageNumber();
        this.size = page.getPageable().getPageSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.numberOfElements = page.getNumberOfElements();
    }

}
