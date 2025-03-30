package com.master.service.impl;

import com.master.dao.entity.State;
import com.master.dao.service.StateMasterDaoSevice;
import com.master.enums.EntityType;
import com.master.request.GetListRequest;
import com.master.request.StateMasterRequest;
import com.master.response.GetListResponse;
import com.master.service.AbstractMasterService;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateMasterService extends AbstractMasterService<StateMasterRequest, StateMasterRequest, String, GetListRequest, GetListResponse<StateMasterRequest>, State> {

    private final StateMasterDaoSevice stateMasterDaoSevice;

    @Override
    public EntityType entityType() {
        return EntityType.STATE_MASTER;
    }

    @Override
    protected StateMasterRequest create(StateMasterRequest request) {
        return stateMasterDaoSevice.create(request);
    }

    @Override
    protected GetListResponse<StateMasterRequest> getAll(int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status) {
        return null;
    }

    @Override
    protected StateMasterRequest get(String request) {
        return stateMasterDaoSevice.get(request);
    }

    @Override
    protected StateMasterRequest update(StateMasterRequest request) throws MasterDataException {
        return stateMasterDaoSevice.update(request);
    }

    @Override
    protected Optional<State> identifyChanges(StateMasterRequest request) {
        return Optional.empty();
    }

    @Override
    protected StateMasterRequest delete(String request) {
        return null;
    }
}
