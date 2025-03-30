package com.master.service;

import com.master.enums.EntityType;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;

import java.util.List;

public interface MasterService<T, R, I, L, C,M> {

    EntityType entityType();

    R create(T request, EntityType type);

    C getAll(EntityType type, int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status);

    R get(I request, EntityType type);

    R update(T request, EntityType type) throws MasterDataException;

    R delete(I request, EntityType type);

    C getByIds(EntityType type, List<I> request);
}
