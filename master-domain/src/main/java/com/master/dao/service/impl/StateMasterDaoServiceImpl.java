package com.master.dao.service.impl;

import com.master.dao.entity.State;
import com.master.dao.repository.StateMasterRepository;
import com.master.dao.service.StateMasterDaoSevice;
import com.master.exception.MasterDataException;
import com.master.request.Pagination;
import com.master.request.SortByRequest;
import com.master.request.StateMasterRequest;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.master.request.StateMasterRequest.buildEntity;
import static com.master.request.StateMasterRequest.buildRequest;

@Service
@RequiredArgsConstructor
public class StateMasterDaoServiceImpl implements StateMasterDaoSevice {

    private final StateMasterRepository stateMasterRepository;

    @Override
    public StateMasterRequest create(StateMasterRequest request) {
        State state = buildEntity(request);
        return buildRequest(stateMasterRepository.save(state));
    }

    @Override
    public StateMasterRequest get(String request) {
        return buildRequest(findEntity(Long.valueOf(request)));
    }

    @Override
    public StateMasterRequest update(StateMasterRequest request) {
        State state = findEntity(request.stateId());
        return buildRequest(stateMasterRepository.save(StateMasterRequest.update(request, state)));
    }

    @Override
    public Page<State> getAllFilter(Pagination pagination, SortByRequest sortByRequest, String searchValue) {
        Pageable pageable = PageRequest.of(pagination.page(), pagination.size(),
                Sort.by(Sort.Direction.fromString(sortByRequest.getValue().getCode()), sortByRequest.getKey()));
        Page<State> states;
        if (StringUtils.isBlank(searchValue)) {
            states = stateMasterRepository.findAll(pageable);
        } else {
            states = stateMasterRepository.findByNameContaining(pageable, searchValue);
        }
        return states;
    }

    private State findEntity(Long stateId) {
        return stateMasterRepository.findById(stateId)
                .orElseThrow(() -> new MasterDataException("Invalid state id: " + stateId));
    }
}
