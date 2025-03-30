package com.master.dao.service.impl;

import com.master.dao.entity.Contact;
import com.master.dao.entity.SafetyServiceProvider;
import com.master.dao.repository.ContactRepository;
import com.master.dao.repository.SafetyServiceProviderRepository;
import com.master.dao.service.SafetyServiceProviderDaoService;
import com.master.enums.SortOrder;
import com.master.exception.MasterDataException;
import com.master.request.Pagination;
import com.master.request.SSMasterRequest;
import com.master.response.GetListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.master.request.SSMasterRequest.*;

@Service
@RequiredArgsConstructor
public class SafetyServiceProviderDaoServiceImpl implements SafetyServiceProviderDaoService {

    private final SafetyServiceProviderRepository safetyServiceProviderRepository;
    private final ContactRepository contactRepository;

    @Override
    public SSMasterRequest create(SSMasterRequest request) {
        SafetyServiceProvider safetyServiceProvider = buildEntity(request);
        List<Contact> allContacts = buildContactEntity(request.contacts(), safetyServiceProvider);
        return buildRequest(safetyServiceProviderRepository.save(safetyServiceProvider),
                contactRepository.saveAll(allContacts));
    }

    @Override
    public SSMasterRequest get(String request) {
        SafetyServiceProvider safetyServiceProvider = getSafetyServiceProviderEntity(Long.valueOf(request));
        return buildRequest(safetyServiceProvider, getAllContacts(safetyServiceProvider));
    }

    private List<Contact> getAllContacts(SafetyServiceProvider safetyServiceProvider) {
        return contactRepository.findAllBySafetyServiceId(safetyServiceProvider.getId());
    }

    private SafetyServiceProvider getSafetyServiceProviderEntity(Long request) {
        return safetyServiceProviderRepository.findById(request)
                .orElseThrow(() -> new MasterDataException("Safety Service Provider not found"));
    }

    @Override
    public SSMasterRequest update(SSMasterRequest request) {
        SafetyServiceProvider ssProvider = getSafetyServiceProviderEntity(request.ssId());
        List<Contact> allContacts = buildContactEntity(request.contacts(), ssProvider);
        return buildRequest(safetyServiceProviderRepository.save(SSMasterRequest.update(request, ssProvider)),
                contactRepository.saveAll(allContacts));
    }

    @Override
    public GetListResponse<SSMasterRequest> getAll(int page, int size, String sortField, List<String> searchFields, String searchValue, String status) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(SortOrder.ASC.getCode()), sortField));
        Page<SafetyServiceProvider> sspPage = safetyServiceProviderRepository.findAll(pageable);
        return prepareAndSendZoneResponse(sspPage, page, size);
    }

    private GetListResponse<SSMasterRequest> prepareAndSendZoneResponse(Page<SafetyServiceProvider> pagedList, int page, int size) {
        List<SSMasterRequest> ssList = new ArrayList<>();
        pagedList.getContent().forEach(ssProvider -> {
            ssList.add(SSMasterRequest.buildRequest(ssProvider, ssProvider.getContacts()));
        });

        Pagination pagination = new Pagination(page, size, pagedList.getTotalPages(),
                pagedList.getTotalElements());

        return GetListResponse.<SSMasterRequest>builder()
                .pagination(pagination)
                .data(ssList)
                .build();

    }
}
