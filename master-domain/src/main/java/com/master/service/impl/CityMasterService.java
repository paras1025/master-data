package com.master.service.impl;

import com.master.dao.entity.City;
import com.master.dao.service.CityMasterDaoService;
import com.master.enums.EntityType;
import com.master.request.CityMasterRequest;
import com.master.request.GetListRequest;
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
public class CityMasterService extends AbstractMasterService<CityMasterRequest, CityMasterRequest, String, GetListRequest, GetListResponse<CityMasterRequest>, City> {

    private final CityMasterDaoService cityMasterDaoService;

    @Override
    public EntityType entityType() {
        return EntityType.CITY_MASTER;
    }

    @Override
    protected CityMasterRequest create(CityMasterRequest request) {
        return cityMasterDaoService.create(request);
    }

    @Override
    protected GetListResponse<CityMasterRequest> getAll(int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status) {
        return null;
    }

    @Override
    protected CityMasterRequest get(String request) {
        return cityMasterDaoService.get(request);
    }

    @Override
    protected CityMasterRequest update(CityMasterRequest request) throws MasterDataException {
        return cityMasterDaoService.update(request);
    }

    @Override
    protected Optional<City> identifyChanges(CityMasterRequest request) {
        return Optional.empty();
    }

    @Override
    protected CityMasterRequest delete(String request) {
        return null;
    }
}
