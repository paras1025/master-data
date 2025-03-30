package com.master.service.impl;

import com.master.dao.entity.City;
import com.master.dao.entity.State;
import com.master.dao.entity.Zone;
import com.master.dao.service.CityMasterDaoService;
import com.master.dao.service.StateMasterDaoSevice;
import com.master.dao.service.ZoneMasterDaoService;
import com.master.enums.SortOrder;
import com.master.request.*;
import com.master.response.GetListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterAdhocService {

    private final CityMasterDaoService cityMasterDaoService;
    private final StateMasterDaoSevice stateMasterDaoSevice;
    private final ZoneMasterDaoService zoneMasterDaoService;

    public GetListResponse<CityMasterRequest> getFilterCities(Long stateId, int page, int size, String sortField, SortOrder sortBy, String searchValue) {
        Pagination pagination = new Pagination(page - 1, size, null, null);
        SortByRequest sortByRequest = SortByRequest.build(sortBy, sortField);
        Page<City> cities = cityMasterDaoService.getAllFilter(pagination, sortByRequest, stateId, searchValue);
        return prepareAndSendCityResponse(cities, page, size);
    }

    private GetListResponse<CityMasterRequest> prepareAndSendCityResponse(Page<City> pagedList, int page, int size) {
        List<CityMasterRequest> cityMasters = new ArrayList<>();
        pagedList.getContent().forEach(cityMasterEntity -> {
            cityMasters.add(CityMasterRequest.buildRequest(cityMasterEntity));
        });

        Pagination pagination = new Pagination(page, size, pagedList.getTotalPages(),
                pagedList.getTotalElements());

        return GetListResponse.<CityMasterRequest>builder()
                .pagination(pagination)
                .data(cityMasters)
                .build();

    }

    public GetListResponse<StateMasterRequest> getFilterStates(int page, int size, String sortField, SortOrder sortBy, String searchValue) {
        Pagination pagination = new Pagination(page - 1, size, null, null);
        SortByRequest sortByRequest = SortByRequest.build(sortBy, sortField);
        Page<State> cities = stateMasterDaoSevice.getAllFilter(pagination, sortByRequest, searchValue);
        return prepareAndSendStateResponse(cities, page, size);
    }

    private GetListResponse<StateMasterRequest> prepareAndSendStateResponse(Page<State> pagedList, int page, int size) {
        List<StateMasterRequest> satteMasters = new ArrayList<>();
        pagedList.getContent().forEach(cityMasterEntity -> {
            satteMasters.add(StateMasterRequest.buildRequest(cityMasterEntity));
        });

        Pagination pagination = new Pagination(page, size, pagedList.getTotalPages(),
                pagedList.getTotalElements());

        return GetListResponse.<StateMasterRequest>builder()
                .pagination(pagination)
                .data(satteMasters)
                .build();

    }

    public GetListResponse<ZoneMasterRequest> getFilterZones(Long cityId, int page, int size, String sortField, SortOrder sortBy, String searchValue) {
        Pagination pagination = new Pagination(page - 1, size, null, null);
        SortByRequest sortByRequest = SortByRequest.build(sortBy, sortField);
        Page<Zone> cities = zoneMasterDaoService.getAllFilter(cityId, pagination, sortByRequest, searchValue);
        return prepareAndSendZoneResponse(cities, page, size);
    }

    private GetListResponse<ZoneMasterRequest> prepareAndSendZoneResponse(Page<Zone> pagedList, int page, int size) {
        List<ZoneMasterRequest> zoneMasters = new ArrayList<>();
        pagedList.getContent().forEach(cityMasterEntity -> {
            zoneMasters.add(ZoneMasterRequest.buildRequest(cityMasterEntity));
        });

        Pagination pagination = new Pagination(page, size, pagedList.getTotalPages(),
                pagedList.getTotalElements());

        return GetListResponse.<ZoneMasterRequest>builder()
                .pagination(pagination)
                .data(zoneMasters)
                .build();

    }
}
