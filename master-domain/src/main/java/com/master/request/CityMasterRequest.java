package com.master.request;

import com.master.dao.entity.City;
import com.master.dao.entity.State;
import com.master.util.MastersMandatory;

import java.time.LocalDateTime;

public record CityMasterRequest(
        @MastersMandatory String cityName,
        Long cityId,
        @MastersMandatory Long stateId,
        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String createdBy,

        String updatedBy

) implements MasterRequest {
    public static City buildEntity(CityMasterRequest request, State state) {
        City city = new City();
        city.setId(request.cityId());
        city.setName(request.cityName());
        city.setState(state);
        return city;
    }

    public static CityMasterRequest buildRequest(City city) {
        return new CityMasterRequest(city.getName(), city.getId(), city.getState().getId(), city.getCreatedAt(),
                city.getUpdatedAt(), city.getCreatedBy(), city.getUpdatedBy());
    }

    public static City update(CityMasterRequest request, City city) {
        city.setName(request.cityName());
        return city;
    }
}
