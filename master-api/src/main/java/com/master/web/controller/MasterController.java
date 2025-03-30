package com.master.web.controller;

import com.master.provider.MasterServiceProvider;
import com.master.request.MasterRequest;
import com.master.service.MasterService;
import com.master.enums.EntityType;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;
import com.master.service.impl.MasterAdhocService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/masters")
@RequiredArgsConstructor
public class MasterController<T extends MasterRequest> {

    private final MasterAdhocService adhocService;

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader("entityType") EntityType entityType, @Valid @RequestBody T entity) {
        MasterService service = MasterServiceProvider.getServiceByEntityType(entityType);
        return new ResponseEntity<>(service.create(entity, entityType), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public <I> ResponseEntity<?> get(@RequestHeader("entityType") EntityType entityType, @PathVariable I id) {
        MasterService service = MasterServiceProvider.getServiceByEntityType(entityType);
        return new ResponseEntity<>(service.get(id, entityType), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestHeader("entityType") EntityType entityType,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                  @RequestParam(value = "sortField") String sortField,
                                  @RequestParam(value = "sortBy") SortOrder sortBy,
                                  @RequestParam(value = "searchField", required = false, defaultValue = "") String searchField,
                                  @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue,
                                  @RequestParam(value = "status", required = false, defaultValue = "") String status) {
        MasterService service = MasterServiceProvider.getServiceByEntityType(entityType);
        List<String> searchFields = new ArrayList<>();
        return new ResponseEntity<>(service.getAll(entityType, page, size, sortField, sortBy, searchFields, searchValue, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public <I> ResponseEntity<?> delete(@RequestHeader("entityType") EntityType entityType, @PathVariable I id) {
        MasterService service = MasterServiceProvider.getServiceByEntityType(entityType);
        return new ResponseEntity<>(service.delete(id, entityType), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestHeader("entityType") EntityType entityType, @Valid @RequestBody T entity) throws MasterDataException {
        MasterService service = MasterServiceProvider.getServiceByEntityType(entityType);
        return new ResponseEntity<>(service.update(entity, entityType), HttpStatus.OK);
    }

    @GetMapping("/list/ids")
    public <I> ResponseEntity<?> getByIds(@RequestHeader("entityType") EntityType entityType,
                                          @RequestParam(value = "ids") List<I> ids) {
        MasterService service = MasterServiceProvider.getServiceByEntityType(entityType);
        return new ResponseEntity<>(service.getByIds(entityType, ids), HttpStatus.OK);
    }

    @GetMapping("{userId}/states")
    public ResponseEntity<?> getFilterStates(@PathVariable Long userId, @RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size,
                                             @RequestParam(value = "sortField") String sortField,
                                             @RequestParam(value = "sortBy") SortOrder sortBy,
                                             @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue) {
        return ResponseEntity.ok(adhocService.getFilterStates(page, size, sortField, sortBy, searchValue));
    }

    @GetMapping("{userId}/cities/{stateId}")
    public ResponseEntity<?> getFilterCities(@PathVariable Long userId, @PathVariable Long stateId, @RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size,
                                             @RequestParam(value = "sortField") String sortField,
                                             @RequestParam(value = "sortBy") SortOrder sortBy,
                                             @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue) {
        return ResponseEntity.ok(adhocService.getFilterCities(stateId, page, size, sortField, sortBy, searchValue));
    }

    @GetMapping("{userId}/zones/{cityId}")
    public ResponseEntity<?> getFilterZones(@PathVariable Long userId, @PathVariable Long cityId, @RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size,
                                             @RequestParam(value = "sortField") String sortField,
                                             @RequestParam(value = "sortBy") SortOrder sortBy,
                                             @RequestParam(value = "searchValue", required = false, defaultValue = "") String searchValue) {
        return ResponseEntity.ok(adhocService.getFilterZones(cityId, page, size, sortField, sortBy, searchValue));
    }

}
