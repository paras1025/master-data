package com.master.dao.service;

import com.master.request.UserMasterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserMasterDaoService {
    
    UserMasterRequest create(UserMasterRequest userMasterDaoService);

    UserMasterRequest get(String request);

    UserMasterRequest update(UserMasterRequest request);
}
