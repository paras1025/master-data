package com.master.request;

import com.master.dao.entity.City;
import com.master.dao.entity.Zone;
import com.master.util.MastersMandatory;

import java.time.LocalDateTime;

public record ZoneMasterRequest(

        Long zoneId,
        @MastersMandatory
        Long cityId,
        @MastersMandatory String zoneName,
        @MastersMandatory Double latitude,
        @MastersMandatory Double longitude,
        @MastersMandatory Double radius,
        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String createdBy,

        String updatedBy
) implements MasterRequest {
    public static Zone buildEntity(ZoneMasterRequest request, City city) {
        Zone zone = new Zone();
        zone.setId(request.zoneId());
        zone.setName(request.zoneName());
        zone.setCity(city);
        zone.setLatitude(request.latitude());
        zone.setLongitude(request.longitude());
        return zone;
    }

    public static ZoneMasterRequest buildRequest(Zone zone) {
        return new ZoneMasterRequest(zone.getId(), zone.getCity().getId(), zone.getName(), zone.getLatitude(),
                zone.getLongitude(), zone.getRadius(), zone.getCreatedAt(), zone.getUpdatedAt(), zone.getCreatedBy(),
                zone.getUpdatedBy());
    }

    public static Zone update(ZoneMasterRequest request, Zone zone, City city) {
        zone.setCity(city);
        zone.setLatitude(request.latitude());
        zone.setLongitude(request.longitude());
        zone.setName(request.zoneName());
        zone.setRadius(request.radius());
        return zone;
    }
}
