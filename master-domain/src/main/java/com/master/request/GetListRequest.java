package com.master.request;


public record GetListRequest(
        Pagination pagination,
        SortByRequest sortBY,
        Long id,
        Long parentId,
        String parentName
) implements MasterRequest {

    public GetListRequest setPagination(Pagination pagination) {
        return new GetListRequest(pagination, sortBY, id, parentId, parentName);
    }
}
