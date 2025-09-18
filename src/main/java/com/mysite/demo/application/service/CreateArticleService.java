package com.mysite.demo.application.service;

import com.mysite.demo.domain.model.Article;
import com.mysite.demo.domain.model.Member;
import com.mysite.demo.domain.port.in.CreateArticleUseCase;
import com.mysite.demo.domain.port.out.ArticlePort;
import com.mysite.demo.domain.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateArticleService implements CreateArticleUseCase {

    private final MemberPort memberPort;
    private final ArticlePort articlePort;

    @Override
    public Article createArticle(CreateArticleCommand command) {
        Member member = memberPort.findByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        if (!member.getPassword().equals(command.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        Article newArticle = Article.withoutId(
                command.title(),
                command.content(),
                member.getId()
        );
        return articlePort.save(newArticle);
    }
}