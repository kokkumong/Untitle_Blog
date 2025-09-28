package com.mysite.demo.adapter.out.persistence;

import com.mysite.demo.adapter.out.persistence.entity.ArticlePersistenceEntity;
import com.mysite.demo.adapter.out.persistence.entity.MemberPersistenceEntity;
import com.mysite.demo.adapter.out.persistence.repository.ArticleJpaRepository;
import com.mysite.demo.adapter.out.persistence.repository.MemberJpaRepository;
import com.mysite.demo.adapter.out.persistence.mapper.ArticleMapper;
import com.mysite.demo.domain.model.Article;
import com.mysite.demo.domain.port.out.ArticlePort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository // <- Spring에 이 클래스를 Bean으로 등록하라는 표시! (가장 중요)
@RequiredArgsConstructor
public class ArticlePersistenceAdapter implements ArticlePort { // <- ArticlePort 인터페이스를 구현

    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleMapper articleMapper;
    // author 정보를 매핑하기 위해 MemberJpaRepository도 필요합니다.
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Article save(Article article) {
        // 1. 작성자(Member)의 영속성 엔티티를 조회합니다.
        MemberPersistenceEntity authorEntity = memberJpaRepository.findById(article.getAuthorId())
                .orElseThrow(() -> new RuntimeException("작성자 정보를 찾을 수 없습니다: " + article.getAuthorId()));

        // 2. 도메인 모델을 JPA 엔티티로 변환합니다.
        ArticlePersistenceEntity articleEntity = articleMapper.toEntity(article, authorEntity);

        // 3. DB에 저장합니다.
        ArticlePersistenceEntity savedEntity = articleJpaRepository.save(articleEntity);

        // 4. 저장된 엔티티를 다시 도메인 모델로 변환하여 반환합니다.
        return articleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return articleJpaRepository.findById(articleId)
                .map(articleMapper::toDomain);
    }
}