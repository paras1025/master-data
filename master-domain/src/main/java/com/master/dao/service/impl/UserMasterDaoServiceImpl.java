package com.master.dao.service.impl;

import com.master.dao.entity.User;
import com.master.dao.entity.UserGeo;
import com.master.dao.repository.UserGeoMasterRepository;
import com.master.dao.repository.UserMasterRepository;
import com.master.dao.service.UserMasterDaoService;
import com.master.exception.MasterDataException;
import com.master.request.UserMasterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.master.request.UserMasterRequest.*;

@Service
@RequiredArgsConstructor
public class UserMasterDaoServiceImpl implements UserMasterDaoService {

    private final UserMasterRepository userMasterRepository;
    private final UserGeoMasterRepository userGeoMasterRepository;

    @Override
    public UserMasterRequest create(UserMasterRequest userMasterRequest) {
        User user = buildEntity(userMasterRequest);
        userMasterRepository.save(user);
        UserGeo userGeo = buildGeoEntity(userMasterRequest, user);
        userGeoMasterRepository.save(userGeo);
        return buildRequest(user, userGeo);
    }

    @Override
    public UserMasterRequest get(String request) {
        User user = findUserEntity(Long.valueOf(request));
        UserGeo userGeo = findUserGeoEntity(user.getId());
        return buildRequest(user, userGeo);
    }

    private UserGeo findUserGeoEntity(Long request) {
        return userGeoMasterRepository.findByUserId(request)
                .orElseThrow(() -> new MasterDataException("Invalid request"));
    }

    private User findUserEntity(Long request) {
        return userMasterRepository.findById(request)
                .orElseThrow(() -> new MasterDataException("Invalid request"));
    }

    @Override
    public UserMasterRequest update(UserMasterRequest request) {
        User user = findUserEntity(request.userId());
        UserGeo userGeo = findUserGeoEntity(user.getId());
        return buildRequest(userMasterRepository.save(UserMasterRequest.update(request, user)),
                userGeoMasterRepository.save(UserMasterRequest.updateGeo(request, userGeo, user)));
    }
}
