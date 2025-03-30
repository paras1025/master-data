package com.master.dao.repository;

import com.master.dao.entity.State;
import com.master.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateMasterRepository extends JpaRepository<State, Long> {

    Page<State> findByNameContaining(Pageable pageable, String searchValue);
}
