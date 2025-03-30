package com.master.request;

import com.master.dao.entity.User;
import com.master.dao.entity.UserGeo;
import com.master.enums.UserType;
import com.master.util.MastersMandatory;

import java.time.LocalDateTime;

public record UserMasterRequest(

        Long userId,
        @MastersMandatory String userName,
        @MastersMandatory UserType userType,
        @MastersMandatory String linkedId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String createdBy,

        String updatedBy

) implements MasterRequest{
    public static User buildEntity(UserMasterRequest userMasterRequest) {
        User user = new User();
        user.setId(userMasterRequest.userId());
        user.setName(userMasterRequest.userName());
        user.setType(userMasterRequest.userType());
        return user;
    }

    public static UserGeo buildGeoEntity(UserMasterRequest userMasterRequest, User user) {
        UserGeo userGeo = new UserGeo();
        userGeo.setId(userMasterRequest.userId());
        userGeo.setLinkedId(userMasterRequest.linkedId());
        userGeo.setUser(user);
        return userGeo;
    }

    public static UserMasterRequest buildRequest(User user, UserGeo userGeo) {
        return new UserMasterRequest(user.getId(), user.getName(), user.getType(), userGeo.getLinkedId(),
                user.getCreatedAt(), user.getUpdatedAt(), user.getCreatedBy(), user.getUpdatedBy());
    }

    public static User update(UserMasterRequest request, User user) {
        user.setName(request.userName());
        user.setType(request.userType());
        return user;
    }

    public static UserGeo updateGeo(UserMasterRequest request, UserGeo userGeo, User user) {
        userGeo.setLinkedId(request.linkedId());
        userGeo.setUser(user);
        return userGeo;
    }
}
