package com.master.service.impl;

import com.master.dao.entity.SafetyServiceProvider;
import com.master.dao.service.SafetyServiceProviderDaoService;
import com.master.enums.EntityType;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;
import com.master.request.GetListRequest;
import com.master.request.SSMasterRequest;
import com.master.response.GetListResponse;
import com.master.service.AbstractMasterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SafetyProviderService extends AbstractMasterService<SSMasterRequest, SSMasterRequest, String, GetListRequest, GetListResponse<SSMasterRequest>, SafetyServiceProvider> {

    private final SafetyServiceProviderDaoService safetyServiceProviderDaoService;

    @Override
    public EntityType entityType() {
        return EntityType.SERVICE_MASTER;
    }

    @Override
    protected SSMasterRequest create(SSMasterRequest request) {
        return safetyServiceProviderDaoService.create(request);
    }

    @Override
    protected GetListResponse<SSMasterRequest> getAll(int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status) {
        return safetyServiceProviderDaoService.getAll(page, size, sortField, searchFields, searchValue, status);
    }

    @Override
    protected SSMasterRequest get(String request) {
        return safetyServiceProviderDaoService.get(request);
    }

    @Override
    protected SSMasterRequest update(SSMasterRequest request) throws MasterDataException {
        return safetyServiceProviderDaoService.update(request);
    }

    @Override
    protected Optional<SafetyServiceProvider> identifyChanges(SSMasterRequest request) {
        return Optional.empty();
    }

    @Override
    protected SSMasterRequest delete(String request) {
        return null;
    }
}
