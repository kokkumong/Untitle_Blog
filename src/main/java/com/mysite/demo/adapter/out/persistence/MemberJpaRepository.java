package com.mysite.demo.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberPersistenceEntity, Long> {
    Optional<MemberPersistenceEntity> findByEmail(String email);
}
