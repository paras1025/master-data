package com.master.dao.repository;

import com.master.dao.entity.User;
import com.master.dao.entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneMasterRepository extends JpaRepository<Zone, Long> {
    Page<Zone> findByCityId(Long cityId, Pageable pageable);

    Page<Zone> findByCityIdAndNameContaining(Long cityId, Pageable pageable, String searchValue);
}
