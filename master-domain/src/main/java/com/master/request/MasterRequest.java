package com.master.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GetListRequest.class, name = "getListRequest"),
        @JsonSubTypes.Type(value = StateMasterRequest.class, name = "stateMaster"),
        @JsonSubTypes.Type(value = CityMasterRequest.class, name = "cityMaster"),
        @JsonSubTypes.Type(value = UserMasterRequest.class, name = "userMaster"),
        @JsonSubTypes.Type(value = ZoneMasterRequest.class, name = "zoneMaster"),
        @JsonSubTypes.Type(value = SSMasterRequest.class, name = "ssMaster")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface MasterRequest {
}
