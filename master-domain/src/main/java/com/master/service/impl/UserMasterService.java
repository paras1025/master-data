package com.master.service.impl;

import com.master.dao.entity.User;
import com.master.dao.service.UserMasterDaoService;
import com.master.enums.EntityType;
import com.master.request.GetListRequest;
import com.master.request.UserMasterRequest;
import com.master.response.GetListResponse;
import com.master.service.AbstractMasterService;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserMasterService extends AbstractMasterService<UserMasterRequest, UserMasterRequest, String, GetListRequest, GetListResponse<UserMasterRequest>, User> {

    private final UserMasterDaoService userMasterDaoService;

    @Override
    public EntityType entityType() {
        return EntityType.USER_MASTER;
    }

    @Override
    protected UserMasterRequest create(UserMasterRequest request) {
        return userMasterDaoService.create(request);
    }

    @Override
    protected GetListResponse<UserMasterRequest> getAll(int page, int size, String sortField, SortOrder sortBy, List<String> searchFields, String searchValue, String status) {
        return null;
    }

    @Override
    protected UserMasterRequest get(String request) {
        return userMasterDaoService.get(request);
    }

    @Override
    protected UserMasterRequest update(UserMasterRequest request) throws MasterDataException {
        return userMasterDaoService.update(request);
    }

    @Override
    protected Optional<User> identifyChanges(UserMasterRequest request) {
        return Optional.empty();
    }

    @Override
    protected UserMasterRequest delete(String request) {
        return null;
    }
}
