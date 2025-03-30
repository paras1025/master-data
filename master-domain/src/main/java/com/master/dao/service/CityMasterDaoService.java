package com.master.dao.service;

import com.master.dao.entity.City;
import com.master.request.CityMasterRequest;
import com.master.request.Pagination;
import com.master.request.SortByRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CityMasterDaoService {

    CityMasterRequest create(CityMasterRequest request);

    CityMasterRequest get(String request);

    CityMasterRequest update(CityMasterRequest request);

    Page<City> getAllFilter(Pagination pagination, SortByRequest sortByRequest, Long stateId, String searchValue);
}
