package com.master.provider;

import com.master.enums.EntityType;
import com.master.service.MasterService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MasterServiceProvider {

    private static Map<EntityType, MasterService> MASTER_HANDLERS = Map.of();

    public MasterServiceProvider(List<MasterService> masterHandlers) {
        MASTER_HANDLERS = masterHandlers.stream()
                .collect(Collectors.toMap(MasterService::entityType, Function.identity()));
    }

    public static MasterService<?, ?, ?, ?, ?, ?> getServiceByEntityType(EntityType entityType) {
        return MASTER_HANDLERS.getOrDefault(entityType, MASTER_HANDLERS.get(EntityType.CUSTOM));

    }
}
