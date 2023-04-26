package com.web.demo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@AllArgsConstructor
@Getter
public class Pagination {
    private int prevPage;
    private int nextPage;
    private int currentPage;
    private boolean hasPrev;
    private boolean hasNext;

    public Pagination(Pageable pageable, Page objects, int currentPage){
        hasPrev = objects.hasPrevious();
        hasNext = objects.hasNext();
        prevPage = pageable.previousOrFirst().getPageNumber();
        nextPage = pageable.next().getPageNumber();
        this.currentPage = currentPage;
    }
}
