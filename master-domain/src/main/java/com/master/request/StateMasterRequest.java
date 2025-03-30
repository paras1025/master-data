package com.master.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.master.dao.entity.State;
import com.master.util.MastersMandatory;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record StateMasterRequest(
        @MastersMandatory String stateName,
        Long stateId,
        @MastersMandatory String countryName,
        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String createdBy,

        String updatedBy

) implements MasterRequest {
    public static State buildEntity(StateMasterRequest request) {
        State state = new State();
        state.setCountry(request.countryName());
        state.setName(request.stateName());
        return state;
    }

    public static StateMasterRequest buildRequest(State state) {
        return new StateMasterRequest(state.getName(), state.getId(), state.getCountry(),
                state.getCreatedAt(), state.getUpdatedAt(), state.getCreatedBy(), state.getUpdatedBy());
    }

    public static State update(StateMasterRequest request, State state) {
        State stateEntity = buildEntity(request);
        stateEntity.setName(state.getName());
        stateEntity.setCountry(state.getCountry());
        return stateEntity;
    }
}
