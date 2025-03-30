package com.master.service;

import com.master.enums.EntityType;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@NoArgsConstructor
public abstract class AbstractMasterService<T, R, I, L, C, M> implements MasterService<T, R, I, L, C, M> {

    protected abstract R create(T request);

    protected abstract C getAll(int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status);

    protected abstract R get(I request);

    protected abstract R update(T request) throws MasterDataException;

    protected abstract Optional<M> identifyChanges(T request);

    protected abstract R delete(I request);

    private static final List<String> entityAuditingFields = List.of("createdBy", "updatedBy", "createdAt", "updatedAt");

    public C getByIds(List<I> request) {
        throw new MasterDataException("Not Supported");
    }

    @Override
    public EntityType entityType() {
        return null;
    }

    @Override
    @Transactional
    public R create(T request, EntityType type) {
        R data = create(request);
        return data;
    }

    @Override
    public C getAll(EntityType type, int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status) {
        return getAll(page, size, sortField, sortBy, searchFields, searchValue, status);
    }

    @Override
    public R get(I request, EntityType type) {
        return get(request);
    }

    @Override
    @Transactional
    public R update(T request, EntityType type) throws MasterDataException {
        R data = update(request);
        return data;
    }

    @Override
    @Transactional
    public R delete(I request, EntityType type) {
        return delete(request);
    }

    @Override
    public C getByIds(EntityType type, List<I> request) {
        return getByIds(request);
    }
}