package com.master.request;

import lombok.Builder;

@Builder
public record Pagination(
        Integer page,
        Integer size,
        Integer totalPages,
        Long totalElements
) {

    public Pagination(Integer page,
                      Integer size,
                      Integer totalPages,
                      Long totalElements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public Pagination setTotalPagesAndElements(Integer totalPages, Long totalElements) {
        return new Pagination(page, size, totalPages, totalElements);
    }

    public Pagination setPageAndSize(Integer page, Integer size) {
        return new Pagination(page, size, totalPages, totalElements);
    }

    public Pagination setSize(Integer size) {
        return new Pagination(page, size, totalPages, totalElements);
    }
}
