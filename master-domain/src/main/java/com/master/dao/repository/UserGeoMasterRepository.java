package com.master.dao.repository;

import com.master.dao.entity.UserGeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGeoMasterRepository extends JpaRepository<UserGeo, Long> {

    Optional<UserGeo> findByUserId(Long id);
}
