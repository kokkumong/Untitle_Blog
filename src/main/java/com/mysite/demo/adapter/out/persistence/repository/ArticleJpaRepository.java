package com.mysite.demo.adapter.out.persistence.repository;

import com.mysite.demo.adapter.out.persistence.entity.ArticlePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<ArticlePersistenceEntity, Long> {
}
