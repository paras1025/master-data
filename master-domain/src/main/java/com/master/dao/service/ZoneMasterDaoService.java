package com.master.dao.service;

import com.master.dao.entity.State;
import com.master.dao.entity.Zone;
import com.master.request.Pagination;
import com.master.request.SortByRequest;
import com.master.request.ZoneMasterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ZoneMasterDaoService {

    ZoneMasterRequest create(ZoneMasterRequest request);

    ZoneMasterRequest get(String request);

    ZoneMasterRequest update(ZoneMasterRequest request);

    Page<Zone> getAllFilter(Long cityId, Pagination pagination, SortByRequest sortByRequest, String searchValue);
}
