package com.master.dao.service;

import com.master.dao.entity.State;
import com.master.request.Pagination;
import com.master.request.SortByRequest;
import com.master.request.StateMasterRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface StateMasterDaoSevice {
    
    StateMasterRequest create(StateMasterRequest request);

    StateMasterRequest get(String request);

    StateMasterRequest update(StateMasterRequest request);

    Page<State> getAllFilter(Pagination pagination, SortByRequest sortByRequest, String searchValue);
}
