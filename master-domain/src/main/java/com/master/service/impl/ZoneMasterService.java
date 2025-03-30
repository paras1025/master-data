package com.master.service.impl;

import com.master.dao.entity.Zone;
import com.master.dao.service.ZoneMasterDaoService;
import com.master.enums.EntityType;
import com.master.request.GetListRequest;
import com.master.request.ZoneMasterRequest;
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
public class ZoneMasterService extends AbstractMasterService<ZoneMasterRequest, ZoneMasterRequest, String, GetListRequest, GetListResponse<ZoneMasterRequest>, Zone> {

    private final ZoneMasterDaoService zoneMasterDaoService;

    @Override
    public EntityType entityType() {
        return EntityType.ZONE_MASTER;
    }

    @Override
    protected ZoneMasterRequest create(ZoneMasterRequest request) {
        return zoneMasterDaoService.create(request);
    }

    @Override
    protected GetListResponse<ZoneMasterRequest> getAll(int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status) {
        return null;
    }

    @Override
    protected ZoneMasterRequest get(String request) {
        return zoneMasterDaoService.get(request);
    }

    @Override
    protected ZoneMasterRequest update(ZoneMasterRequest request) throws MasterDataException {
        return zoneMasterDaoService.update(request);
    }

    @Override
    protected Optional<Zone> identifyChanges(ZoneMasterRequest request) {
        return Optional.empty();
    }

    @Override
    protected ZoneMasterRequest delete(String request) {
        return null;
    }
}
