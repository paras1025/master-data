package com.master.dao.service;

import com.master.request.SSMasterRequest;
import com.master.response.GetListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SafetyServiceProviderDaoService {

    SSMasterRequest create(SSMasterRequest request);

    SSMasterRequest get(String request);

    SSMasterRequest update(SSMasterRequest request);

    GetListResponse<SSMasterRequest> getAll(int page, int size, String sortField, List<String> searchFields, String searchValue, String status);
}
