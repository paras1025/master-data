package com.master.request;

import com.master.dao.entity.Contact;
import com.master.dao.entity.SafetyServiceProvider;
import com.master.util.MastersMandatory;

import java.time.LocalDateTime;
import java.util.List;

public record SSMasterRequest(

        Long ssId,
        @MastersMandatory
        String name,
        @MastersMandatory String address,
        @MastersMandatory List<ContactInfo> contacts,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String createdBy,

        String updatedBy
) implements MasterRequest {

    public static List<Contact> buildContactEntity(@MastersMandatory List<ContactInfo> contacts, SafetyServiceProvider safetyServiceProvider) {
        return contacts.stream().map(ci -> buildContact(ci, safetyServiceProvider)).toList();
    }

    private static Contact buildContact(ContactInfo contactInfo, SafetyServiceProvider safetyServiceProvider) {
        Contact contact = new Contact();
        contact.setContactName(contactInfo.name());
        contact.setContactNumber(contactInfo.phoneNumber());
        contact.setSafetyService(safetyServiceProvider);
        return contact;
    }

    public static SafetyServiceProvider update(SSMasterRequest request, SafetyServiceProvider safetyServiceProvider) {
        safetyServiceProvider.setName(request.name());
        safetyServiceProvider.setAddress(request.address());
        safetyServiceProvider.setLatitude(request.latitude());
        safetyServiceProvider.setLongitude(request.longitude());
        return safetyServiceProvider;
    }

    public record ContactInfo(
            @MastersMandatory
            String name,
            String phoneNumber
    ) {

    }

    public static SafetyServiceProvider buildEntity(SSMasterRequest request) {
        SafetyServiceProvider service = new SafetyServiceProvider();
        service.setAddress(request.address());
        service.setName(request.name());
        service.setLatitude(request.latitude());
        service.setLongitude(request.longitude());
        return service;
    }

    public static SSMasterRequest buildRequest(SafetyServiceProvider safetyService, List<Contact> contacts) {
        return new SSMasterRequest(safetyService.getId(), safetyService.getName(), safetyService.getAddress(),
                buildContacts(contacts), safetyService.getLatitude(), safetyService.getLongitude(),
                safetyService.getCreatedAt(), safetyService.getUpdatedAt(), safetyService.getCreatedBy(),
                safetyService.getUpdatedBy());
    }

    private static @MastersMandatory List<ContactInfo> buildContacts(List<Contact> contacts) {
        return contacts.stream().map(e -> new ContactInfo(e.getContactName(), e.getContactNumber())).toList();
    }
}
