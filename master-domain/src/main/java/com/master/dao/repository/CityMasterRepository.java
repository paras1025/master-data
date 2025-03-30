package com.master.dao.repository;

import com.master.dao.entity.City;
import com.master.dao.entity.User;
import com.master.request.CityMasterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityMasterRepository extends JpaRepository<City, Long> {

    Page<City> findByStateId(Long stateId, Pageable pageable);

    Page<City> findByStateIdAndNameContaining(Long stateId, Pageable pageable, String searchValue);
}
