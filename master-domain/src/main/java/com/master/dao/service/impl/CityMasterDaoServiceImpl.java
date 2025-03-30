package com.master.dao.service.impl;

import com.master.dao.entity.City;
import com.master.dao.entity.State;
import com.master.dao.repository.CityMasterRepository;
import com.master.dao.repository.StateMasterRepository;
import com.master.dao.service.CityMasterDaoService;
import com.master.exception.MasterDataException;
import com.master.request.CityMasterRequest;
import com.master.request.Pagination;
import com.master.request.SortByRequest;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.master.request.CityMasterRequest.buildEntity;
import static com.master.request.CityMasterRequest.buildRequest;

@Service
@RequiredArgsConstructor
public class CityMasterDaoServiceImpl implements CityMasterDaoService {

    private final CityMasterRepository cityMasterRepository;
    private final StateMasterRepository stateMasterRepository;

    @Override
    public CityMasterRequest create(CityMasterRequest request) {
        State state = stateMasterRepository.findById(request.stateId()).orElseThrow(() -> new MasterDataException("Invalid state Id: " + request.stateId()));
        City city = buildEntity(request, state);
        return buildRequest(cityMasterRepository.save(city));
    }

    @Override
    public CityMasterRequest get(String id) {
        return buildRequest(findEntity(Long.valueOf(id)));
    }

    @Override
    public CityMasterRequest update(CityMasterRequest request) {
        City city = findEntity(request.cityId());
        return buildRequest(cityMasterRepository.save(CityMasterRequest.update(request, city)));
    }

    @Override
    public Page<City> getAllFilter(Pagination pagination, SortByRequest sortByRequest, Long stateId, String searchValue) {
        Pageable pageable = PageRequest.of(pagination.page(), pagination.size(),
                Sort.by(Sort.Direction.fromString(sortByRequest.getValue().getCode()), sortByRequest.getKey()));
        Page<City> cities;
        if (StringUtils.isBlank(searchValue)) {
            cities = cityMasterRepository.findByStateId(stateId, pageable);
        } else {
            cities = cityMasterRepository.findByStateIdAndNameContaining(stateId, pageable, searchValue);
        }
        return cities;
    }

    private City findEntity(Long cityId) {
        return cityMasterRepository.findById(cityId)
                .orElseThrow(() -> new MasterDataException("Invalid city id: " + cityId));
    }
}
