package com.master.dao.service.impl;

import com.master.dao.entity.City;
import com.master.dao.entity.State;
import com.master.dao.entity.Zone;
import com.master.dao.repository.CityMasterRepository;
import com.master.dao.repository.UserMasterRepository;
import com.master.dao.repository.ZoneMasterRepository;
import com.master.dao.service.ZoneMasterDaoService;
import com.master.exception.MasterDataException;
import com.master.request.Pagination;
import com.master.request.SortByRequest;
import com.master.request.ZoneMasterRequest;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.master.request.ZoneMasterRequest.buildEntity;
import static com.master.request.ZoneMasterRequest.buildRequest;

@Service
@RequiredArgsConstructor
public class ZoneMasterDaoServiceImpl implements ZoneMasterDaoService {

    private final ZoneMasterRepository zoneMasterRepository;
    private final CityMasterRepository cityMasterRepository;

    @Override
    public ZoneMasterRequest create(ZoneMasterRequest request) {
        City city = findCity(request);
        Zone zone = buildEntity(request, city);
        return buildRequest(zoneMasterRepository.save(zone));
    }

    private City findCity(ZoneMasterRequest request) {
        return cityMasterRepository.findById(request.cityId()).orElseThrow(() -> new MasterDataException("City not found"));
    }

    @Override
    public ZoneMasterRequest get(String request) {
        Zone zone = findEntity(Long.valueOf(request));
        return buildRequest(zone);
    }

    @Override
    public ZoneMasterRequest update(ZoneMasterRequest request) {
        City city = findCity(request);
        Zone zone = findEntity(request.zoneId());
        return buildRequest(zoneMasterRepository.save(ZoneMasterRequest.update(request, zone, city)));
    }

    @Override
    public Page<Zone> getAllFilter(Long cityId, Pagination pagination, SortByRequest sortByRequest, String searchValue) {
        Pageable pageable = PageRequest.of(pagination.page(), pagination.size(),
                Sort.by(Sort.Direction.fromString(sortByRequest.getValue().getCode()), sortByRequest.getKey()));
        Page<Zone> zones = null;
        if(StringUtils.isBlank(searchValue)) {
            zones = zoneMasterRepository.findByCityId(cityId, pageable);
        }else{
            zones =  zoneMasterRepository.findByCityIdAndNameContaining(cityId, pageable,searchValue);
        }
        return zones;
    }

    private Zone findEntity(Long zoneId) {
        return zoneMasterRepository.findById(zoneId)
                .orElseThrow(() -> new MasterDataException("Invalid state id: " + zoneId));
    }
}
