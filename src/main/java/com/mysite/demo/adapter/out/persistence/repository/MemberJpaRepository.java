package com.mysite.demo.adapter.out.persistence.repository;

import com.mysite.demo.adapter.out.persistence.entity.MemberPersistenceEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberPersistenceEntity, Long> {
    Optional<MemberPersistenceEntity> findByEmail(String email);
}
