package com.master.dao.repository;

import com.master.dao.entity.SafetyServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyServiceProviderRepository extends JpaRepository<SafetyServiceProvider, Long> {
}
